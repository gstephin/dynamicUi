package com.baashaa.dynamiclayout.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baashaa.dynamiclayout.R
import com.baashaa.dynamiclayout.model.Links

/**
 * Created by Stephin on 13-07-2020.
 */
class ChildAdapter(val links: List<Links>) : RecyclerView.Adapter<ChildAdapter.ChildHolder>() {
    class ChildHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_child_item, parent, false)
        return ChildHolder(rootView!!)
    }

    override fun getItemCount(): Int {
        return links.size
    }

    override fun onBindViewHolder(holder: ChildHolder, position: Int) {
        val get = links[position]
        holder.tvTitle.text = get.title
    }
}