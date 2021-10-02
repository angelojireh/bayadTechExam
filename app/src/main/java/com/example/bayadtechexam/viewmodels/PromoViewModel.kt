package com.example.bayadtechexam.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bayadtechexam.models.PromoModel
import com.example.bayadtechexam.models.PromoRepo

class PromoViewModel(application: Application): AndroidViewModel(application) {

    private var promoRepo : PromoRepo? = null
    var promoModelListLiveData:LiveData<List<PromoModel>>? = null

    init {
        promoRepo = PromoRepo()
        promoModelListLiveData = MutableLiveData()
    }

    fun fetchAllPromos(){
        promoModelListLiveData = promoRepo?.fetchAllPromos()
    }
}