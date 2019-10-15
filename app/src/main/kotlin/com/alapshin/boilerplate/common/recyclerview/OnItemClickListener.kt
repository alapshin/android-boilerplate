package com.alapshin.boilerplate.common.recyclerview

interface OnItemClickListener<T> {
    fun onItemClick(item: T?, position: Int)
}
