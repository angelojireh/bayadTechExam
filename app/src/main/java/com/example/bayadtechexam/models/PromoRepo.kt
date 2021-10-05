package com.example.bayadtechexam.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bayadtechexam.network.ApiClient
import com.example.bayadtechexam.network.ApiInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromoRepo {
    private val HTTP_OK = 200
    private var apiInterface:ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

    fun fetchAllPromos():LiveData<List<PromoModel>>{

        val data = MutableLiveData<List<PromoModel>>()

        //call api interface GET method
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

    fun deletePromo(id: String): LiveData<Boolean>{

        val data = MutableLiveData<Boolean>()

        apiInterface.deletePromo(id).enqueue(object: Callback<Void> {

            override fun onFailure(call: Call<Void>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                data.value = response.code() == HTTP_OK
            }
        })
        return data
    }

    fun updatePromo(id: String, updateModel: UpdateModel): LiveData<Boolean>{

        val data = MutableLiveData<Boolean>()

        apiInterface.updatePromo(id, updateModel).enqueue(object: Callback<ResponseBody>{

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                data.value = response.code() == HTTP_OK
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                data.value = false
            }
        })
        return data
    }
}