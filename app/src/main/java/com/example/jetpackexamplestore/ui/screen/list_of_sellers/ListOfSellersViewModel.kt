package com.example.jetpackexamplestore.ui.screen.list_of_sellers

import com.example.jetpackexamplestore.app.App
import com.example.jetpackexamplestore.model.Seller

class ListOfSellersViewModel {

    val sellers: List<Seller> = App.sellers ?: emptyList()

}