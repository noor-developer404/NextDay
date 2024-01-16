package com.developer404.nestday.others

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developer404.nestday.R
import com.developer404.nestday.apis.fav_click
import com.google.android.material.imageview.ShapeableImageView

class adapter(var mutableLiveData: MutableLiveData<dataClass>, var context :Context, var face:fav_click) :
    RecyclerView.Adapter<adapter.holder>() {


    class holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ShapeableImageView>(R.id.item_img)
        val id=itemView.findViewById<TextView>(R.id.item_id)
        val name=itemView.findViewById<TextView>(R.id.item_name)
        val mail=itemView.findViewById<TextView>(R.id.item_mail)
        val fav = itemView.findViewById<ImageView>(R.id.item_fav)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return holder(view)
    }

    override fun getItemCount(): Int {
       return mutableLiveData.value?.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.id.text="id: "+mutableLiveData.value?.data?.get(position)?.id.toString()
        holder.name.text= mutableLiveData.value?.data?.get(position)?.first_name.toString()+" "+mutableLiveData.value?.data?.get(position)?.last_name.toString()
        holder.mail.text= mutableLiveData.value?.data?.get(position)?.email.toString()
        Glide.with(context).load(mutableLiveData.value?.data?.get(position)?.avatar.toString()).into(holder.img)

        holder.fav.setOnClickListener {
            face.clicked(mutableLiveData.value?.data!![position].id,holder.itemView)
        }
    }

    fun update(data: MutableLiveData<dataClass>){
        this.mutableLiveData=data
        notifyDataSetChanged()
    }
}