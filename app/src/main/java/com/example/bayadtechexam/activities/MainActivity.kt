package com.example.bayadtechexam.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bayadtechexam.R
import com.example.bayadtechexam.adapters.PromoAdapter
import com.example.bayadtechexam.models.PromoModel
import com.example.bayadtechexam.viewmodels.PromoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mPromoViewModel: PromoViewModel
    private lateinit var mPromoAdapter: PromoAdapter
    private lateinit var mGridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        loadPromos()
    }

    private fun initAdapter() {
        mGridLayoutManager = GridLayoutManager(this, 1)
        mPromoAdapter = PromoAdapter(this)
        recyclerView_promos.setHasFixedSize(true)
        recyclerView_promos.layoutManager = mGridLayoutManager
        recyclerView_promos.adapter = mPromoAdapter
    }

    private fun loadPromos(){
        mPromoViewModel = ViewModelProvider(this)[PromoViewModel::class.java]
        mPromoViewModel.fetchAllPromos()
        mPromoViewModel.promoModelListLiveData?.observe(this, Observer {
            if (it != null){
                progressBar_promos.visibility = View.GONE
                recyclerView_promos.visibility = View.VISIBLE
                mPromoAdapter.setData(it as ArrayList<PromoModel>)
            }
            else{
                progressBar_promos.visibility = View.GONE
                tv_no_promos.visibility = View.VISIBLE
            }
        })
    }
}