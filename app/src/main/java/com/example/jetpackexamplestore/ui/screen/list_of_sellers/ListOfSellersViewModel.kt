package com.example.jetpackexamplestore.ui.screen.list_of_sellers

import com.example.jetpackexamplestore.store.Store
import com.example.jetpackexamplestore.store.entities.Seller

class ListOfSellersViewModel {

    val sellers: List<Seller> = Store.sellers ?: emptyList()

}