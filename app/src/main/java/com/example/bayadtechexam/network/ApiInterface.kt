package com.example.bayadtechexam.network

import com.example.bayadtechexam.models.PromoModel
import com.example.bayadtechexam.models.UpdateModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("bayad")
    fun fetchAllPromos(): Call<List<PromoModel>>

    @DELETE("bayad/{id}")
    fun deletePromo(@Path("id") id:String): Call<Void>

    @PUT("bayad/{id}")
    fun updatePromo(@Path("id") id:String, @Body updateModel: UpdateModel): Call<ResponseBody>
}