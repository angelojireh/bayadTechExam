package com.example.bayadtechexam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bayadtechexam.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_promo.*

class PromoActivity : AppCompatActivity(), View.OnClickListener {

    private var name: String? = null
    private var details: String? = null
    private var image_url: String? = null
    private var read: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo)

        initButtonListener()
        loadPromo()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_back -> this.finish()
        }
    }

    private fun loadPromo() {
        name = intent.getStringExtra("name")
        details = intent.getStringExtra("details")
        image_url = intent.getStringExtra("image_url")
        read = intent.getIntExtra("read", 0)

        //set image
        Picasso.get()
            .load(image_url)
            .resize(450, 450)
            .centerCrop()
            .into(imageview_promo)

        //set details
        tv_promo_details.text = details
    }

    private fun initButtonListener(){
        button_back.setOnClickListener(this)
    }
}