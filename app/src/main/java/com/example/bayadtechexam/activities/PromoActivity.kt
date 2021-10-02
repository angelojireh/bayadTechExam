package com.example.bayadtechexam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bayadtechexam.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_promo.*

class PromoActivity : AppCompatActivity() {

    private var name: String? = null
    private var details: String? = null
    private var image_url: String? = null
    private var read: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo)

        loadPromos()
    }

    private fun loadPromos() {
        name = intent.getStringExtra("name")
        details = intent.getStringExtra("details")
        image_url = intent.getStringExtra("image_url")
        read = intent.getIntExtra("read", 0)

        Picasso.get()
            .load(image_url)
            .centerCrop()
            .into(imageview_promo)

        tv_promo_details.text = details
    }
}