package com.baashaa.dynamiclayout.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baashaa.dynamiclayout.R
import com.baashaa.dynamiclayout.model.Header
import kotlinx.android.synthetic.main.rv_header_item.view.*

/**
 * Created by Stephin on 13-07-2020.
 */
class RecyclerHeaderAdapter(val list: List<Header>?) :
    RecyclerView.Adapter<RecyclerHeaderAdapter.Holder>() {
    private val childRecycler = RecyclerView.RecycledViewPool()

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvChild: RecyclerView = itemView.rvChild
        val tvTitle: TextView = itemView.tvTitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_header_item, parent, false)
        return Holder(rootView!!)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val get = list!![position]
        holder.tvTitle.text = get.title
        val childLayoutManager =
            LinearLayoutManager(holder.rvChild.context, RecyclerView.HORIZONTAL, false)
        holder.rvChild.apply {
            layoutManager = childLayoutManager
            adapter = ChildAdapter(get.links)
            setRecycledViewPool(childRecycler)
        }
    }
}