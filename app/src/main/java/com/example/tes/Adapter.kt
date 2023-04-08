package com.example.tes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class Adapter(private val data: List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {



    class viewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.title)
        var priority :TextView= itemView.findViewById(R.id.priority)
        var btnMore : Button = itemView.findViewById(R.id.btnMore)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.btnMore.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateListActivity::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)

        }
    }


}