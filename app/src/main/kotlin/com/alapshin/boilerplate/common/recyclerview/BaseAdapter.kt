package com.alapshin.boilerplate.common.recyclerview

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder>(itemCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(itemCallback) {

    interface OnItemClickListener<T> {
        fun onItemClick(item: T, position: Int)
    }

    var onItemClickListener: OnItemClickListener<T>? = null

    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        onItemClickListener?.let { listener ->
            holder.itemView.setOnClickListener {
                listener.onItemClick(getItem(holder.adapterPosition), holder.adapterPosition)
            }
        }
    }
}
