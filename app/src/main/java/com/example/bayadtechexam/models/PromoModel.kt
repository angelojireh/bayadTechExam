package com.example.bayadtechexam.models

class PromoModel : ArrayList<PromoModelItem>()

data class PromoModelItem(
    val _id: String,
    val name: String,
    val details: String,
    val image_url: String,
    val read: Int
)