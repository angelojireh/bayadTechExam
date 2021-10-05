package com.example.bayadtechexam.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bayadtechexam.models.PromoModel
import com.example.bayadtechexam.models.PromoRepo
import com.example.bayadtechexam.models.UpdateModel

class PromoViewModel(application: Application): AndroidViewModel(application) {

    private var promoRepo : PromoRepo? = null

    var promoModelListLiveData:LiveData<List<PromoModel>>? = null
    var deletePromoLiveData:LiveData<Boolean>? = null
    var updatePromoLiveData:LiveData<Boolean>? = null

    init {
        promoRepo = PromoRepo()
        promoModelListLiveData = MutableLiveData()
        deletePromoLiveData = MutableLiveData()
    }

    fun fetchAllPromos(){
        promoModelListLiveData = promoRepo?.fetchAllPromos()
    }

    fun deletePromo(id:String){
        deletePromoLiveData = promoRepo?.deletePromo(id)
    }

    fun updatePromo(id: String, updateModel: UpdateModel){
        updatePromoLiveData = promoRepo?.updatePromo(id, updateModel)
    }
}