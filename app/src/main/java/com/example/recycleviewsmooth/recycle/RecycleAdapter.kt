package com.example.recycleviewsmooth.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleviewsmooth.R
import kotlinx.android.synthetic.main.itemrecycle.view.*

class RecycleAdapter : RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private var listItem: ArrayList<String> = ArrayList()

    constructor(list: ArrayList<String>) {
        this.listItem = list
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(title: String) {
            Glide.with(itemView).load(title).into(itemView.findViewById(R.id.tv_item))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdapter.MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater.inflate(R.layout.itemrecycle, parent, false))
    }

    override fun getItemCount(): Int {
        return if (listItem == null) 0 else listItem.size
    }

    override fun onBindViewHolder(holder: RecycleAdapter.MyViewHolder, position: Int) {
        holder.onBind(listItem.get(position))
    }
}