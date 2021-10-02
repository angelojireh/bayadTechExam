package com.example.bayadtechexam.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bayadtechexam.R
import com.example.bayadtechexam.models.PromoModel
import com.example.bayadtechexam.viewmodels.PromoDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.promo_rcv_itemview.view.*


class PromoAdapter(val context: Activity): RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    private var data : ArrayList<PromoModel>? = null

    fun setData(list: ArrayList<PromoModel>){
        data = list
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        return  PromoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.promo_rcv_itemview, parent, false))
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class PromoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val pos: Int = bindingAdapterPosition
            if(pos != RecyclerView.NO_POSITION) {
                val mPromoDetails = data?.let { PromoDetails(context, pos, it as ArrayList<PromoModel>) }
                mPromoDetails?.setPromoDetails()
            }
        }

        fun bindView(item: PromoModel?){
            if(item?.image_url != null && item.image_url.isNotEmpty()){
                loadImage(item.image_url, itemView.imageview_promo)
            }
            else{
                val no_image = "https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png"
                loadImage(no_image, itemView.imageview_promo)
            }
            itemView.tv_promo_name.text = item?.name
        }

        fun loadImage(image: String, imageView: ImageView){
            Picasso.get()
                .load(image)
                .centerCrop()
                .into(imageView)
        }
    }
}