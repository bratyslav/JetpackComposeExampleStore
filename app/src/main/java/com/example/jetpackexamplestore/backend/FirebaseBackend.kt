package com.example.jetpackexamplestore.backend

import com.example.jetpackexamplestore.store.Backend
import com.example.jetpackexamplestore.store.entities.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.ktx.Firebase

class FirebaseBackend : Backend {

    private val firebaseUser = FirebaseAuth.getInstance().currentUser
    private val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()

    override fun downloadCustomerProfile(
        onSuccess: (Customer) -> Unit,
        onFailure: () -> Unit
    ) {
        val userId = firebaseUser?.uid ?: run {
            onFailure()
            return
        }
        db.collection("customers").document(userId).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val name = it["name"] as? String ?: "noname"
                    val surname = it["surname"] as? String ?: "nosurname"
                    val phoneNum = it["phone"] as? String ?: "nophone"
                    val customer = Customer(name, surname, phoneNum)
                    onSuccess(customer)
                } else {
                    onFailure()
                }
            }
            .addOnFailureListener { onFailure() }
            .addOnCanceledListener(onFailure)
    }

    override fun updateCustomerProfile(
        customer: Customer,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val userId = firebaseUser?.uid ?: return
        val userDocument = hashMapOf<String, Any>(
            "name" to customer.name,
            "surname" to customer.surname,
            "phone" to customer.phone,
        )
        db.collection("customers").document(userId).set(userDocument, SetOptions.merge())
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure() }
            .addOnCanceledListener(onFailure)
    }

    override fun getSellerProducts(
        sellerId: String,
        onSuccess: (List<Product>) -> Unit,
        onFailure: () -> Unit
    ) {
        db.collection("sellers").document(sellerId).collection("products").get()
            .addOnSuccessListener {
                val products = mutableListOf<Product>()
                for (document in it.documents) {
                    val name = document["name"] as? String ?: "noname"
                    val description = document["description"] as? String ?: "nosurname"
                    val price = castToPrice(document["price"])
                    val imagesNames = document["images"] as? List<String> ?: emptyList()
                    val product = Product(name, description, price, sellerId, document.id)
                    product.imagesNames.addAll(imagesNames)
                    products.add(product)
                }
                onSuccess(products)
            }
            .addOnFailureListener { onFailure() }
            .addOnCanceledListener(onFailure)
    }

    override fun getAllSellersList(onSuccess: (List<Seller>) -> Unit, onFailure: () -> Unit) {
        db.collection("sellers").get()
            .addOnSuccessListener {
                val sellers = mutableListOf<Seller>()
                for (document in it.documents) {
                    val company = (document["company"] as? String) ?: "No Company Name"
                    val seller = Seller("", "", "", company, document.id)
                    sellers.add(seller)
                }
                onSuccess(sellers)
            }
            .addOnFailureListener { onFailure() }
            .addOnCanceledListener(onFailure)
    }

    override fun downloadImage(
        relativeUrl: String,
        onSuccess: (ByteArray) -> Unit,
        onFailure: () -> Unit
    ) {
        val storageRef = storage.reference
        val imageRef = storageRef.child(relativeUrl)

        imageRef.getBytes(1024 * 1024 * 100)
            .addOnSuccessListener { onSuccess(it) }
            .addOnFailureListener { onFailure() }
            .addOnCanceledListener(onFailure)
    }

    override fun createOrder(order: Order, onSuccess: () -> Unit, onFailure: () -> Unit) {}

    private fun castToPrice(obj: Any?): Float {
        var price = 0f
        try {
            val priceAsDouble = obj as Double
            price = priceAsDouble.toFloat()
        } catch (e: Exception) {
            try {
                val priceAsLong = obj as Long
                price = priceAsLong.toFloat()
            } catch (e: Exception) { }
        }
        return price
    }

}