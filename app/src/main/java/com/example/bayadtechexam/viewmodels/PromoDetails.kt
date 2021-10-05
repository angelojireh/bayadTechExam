package com.example.bayadtechexam.viewmodels

import android.app.Activity
import android.content.Intent
import com.example.bayadtechexam.activities.PromoActivity
import com.example.bayadtechexam.models.PromoModel

class PromoDetails(val context: Activity, val position: Int, var data: ArrayList<PromoModel>) {

    fun setPromoDetails(){
        val NO_IMAGE_URL = "https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png"
        val _id = data[position]._id
        val name = data[position].name
        val details = data[position].details
        val image_url = if((data[position].image_url).isNotEmpty())
            data[position].image_url else NO_IMAGE_URL
        val read = data[position].read

        gotoPromoActivity(context, _id, name, details, image_url, read)
    }

    fun gotoPromoActivity(context: Activity, _id: String, name: String, details: String, image_url: String, read: Int){
        val i =Intent(context, PromoActivity::class.java)
        i.putExtra("_id", _id)
        i.putExtra("name", name)
        i.putExtra("details", details)
        i.putExtra("image_url", image_url)
        i.putExtra("read", read)
        context.startActivity(i)
    }
}