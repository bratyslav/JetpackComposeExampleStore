package com.example.jetpackexamplestore.model

data class Seller(
    val name: String,
    val surname: String,
    val phone: String,
    val company: String,
    val id: String = ""
)