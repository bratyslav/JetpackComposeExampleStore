package com.example.jetpackexamplestore.model

data class Product(
    val name: String,
    val description: String,
    val price: Float,
    val ownerId: String,
    val id: String,
    val imagesNames: MutableList<String> = mutableListOf(),
    val count: Int = 1
)