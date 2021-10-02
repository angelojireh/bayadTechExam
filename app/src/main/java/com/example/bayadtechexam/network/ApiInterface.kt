package com.example.bayadtechexam.network

import com.example.bayadtechexam.models.PromoModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("bayad")
    fun fetchAllPromos(): Call<List<PromoModel>>
}