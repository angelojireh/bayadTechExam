package com.example.bayadtechexam.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bayadtechexam.R
import com.example.bayadtechexam.models.PromoModel
import com.example.bayadtechexam.viewmodels.PromoDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.promo_rcv_itemview.view.*


class PromoAdapter(val context: Activity, val deleteListener: DeleteListener): RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    private var data : ArrayList<PromoModel>? = null

    fun setData(list: ArrayList<PromoModel>){
        data = list
        notifyDataSetChanged()
    }

    fun deleteData(position: Int){
        data?.removeAt(position)
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

    inner class PromoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener{

        //initialize item click/long click listeners
        init {
            itemView.isClickable = true
            itemView.isLongClickable = true
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        //when row is clicked
        override fun onClick(v: View?) {
            val pos: Int = bindingAdapterPosition
            if(pos != RecyclerView.NO_POSITION) {
                //send arraylist based on position to promo details
                val mPromoDetails = data?.let { PromoDetails(context, pos, it as ArrayList<PromoModel>) }
                mPromoDetails?.setPromoDetails()
            }
        }

        //when row is long clicked
        override fun onLongClick(p0: View?): Boolean {
            val pos: Int = bindingAdapterPosition
            if(pos != RecyclerView.NO_POSITION) {
                data?.get(pos)?.let { deleteListener.onItemDelete(it, pos) }
            }
            return true
        }

        fun bindView(item: PromoModel?){
            //set image and textview values
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
                .resize(450, 450)
                .centerCrop()
                .into(imageView)
        }
    }

    //lets main activity know which promo to delete
    interface DeleteListener{
        fun onItemDelete(promoModel: PromoModel, position: Int)
    }
}