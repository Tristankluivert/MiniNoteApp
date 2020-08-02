package com.kluivert.mininoteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kluivert.mininoteapp.R
import com.kluivert.mininoteapp.data.entities.SlideModel
import kotlinx.android.synthetic.main.slide_item.view.*

class SlideAdapter (

    var slideList : List<SlideModel>

) : RecyclerView.Adapter<SlideAdapter.SlideAdapterViewHolder>(){


   inner class SlideAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideAdapterViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item,parent,false)
        return SlideAdapterViewHolder(view)

    }

    override fun getItemCount(): Int {
        return slideList.size
    }

    override fun onBindViewHolder(holder: SlideAdapterViewHolder, position: Int) {

        holder.itemView.apply {
            val current = slideList[position]
            ivSlideImage.setImageResource(current.slideimage)
            tvTitle.text = current.slidetitle
            tvDesc.text = current.slidedesc
        }

    }

}