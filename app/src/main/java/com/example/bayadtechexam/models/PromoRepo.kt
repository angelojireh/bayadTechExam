package com.example.bayadtechexam.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bayadtechexam.network.ApiClient
import com.example.bayadtechexam.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromoRepo {
    private val HTTP_OK = 200
    private var apiInterface:ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

    fun fetchAllPromos():LiveData<List<PromoModel>>{

        val data = MutableLiveData<List<PromoModel>>()

        apiInterface.fetchAllPromos().enqueue(object: Callback<List<PromoModel>>{
            override fun onFailure(call: Call<List<PromoModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<PromoModel>>, response: Response<List<PromoModel>>) {
                val res = response.body()
                if(response.code() == HTTP_OK && res!=null){
                    data.value = res
                } else data.value = null
            }
        })
        return data
    }
}