package com.example.bayadtechexam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bayadtechexam.R
import com.example.bayadtechexam.models.UpdateModel
import com.example.bayadtechexam.viewmodels.PromoViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_promo.*

class PromoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mPromoViewModel: PromoViewModel

    private var _id: String? = null
    private var name: String? = null
    private var details: String? = null
    private var image_url: String? = null
    private var read: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo)

        initButtonListener()
        loadPromo()
        updatePromo()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_back -> this.finish()
        }
    }

    private fun loadPromo() {
        _id = intent.getStringExtra("_id")
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

    private fun updatePromo() {
        //update promo as 'read' once loaded
        val updatemodel = UpdateModel(name!!, details!!, image_url!!, 1)
        mPromoViewModel = ViewModelProvider(this)[PromoViewModel::class.java]
        mPromoViewModel.updatePromo(_id!!, updatemodel)
        mPromoViewModel.updatePromoLiveData?.observe(this, Observer {
            if(it != false){
                Snackbar.make(view_promo, "Promo marked as read", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun initButtonListener(){
        button_back.setOnClickListener(this)
    }
}