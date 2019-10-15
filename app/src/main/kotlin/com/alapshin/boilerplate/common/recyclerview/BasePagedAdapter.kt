package com.alapshin.boilerplate.common.recyclerview

import androidx.annotation.CallSuper
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagedAdapter<T, VH : RecyclerView.ViewHolder>(itemCallback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, VH>(itemCallback) {

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
