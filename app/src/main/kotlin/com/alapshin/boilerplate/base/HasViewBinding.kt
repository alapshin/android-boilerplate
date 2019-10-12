package com.alapshin.boilerplate.base

import androidx.viewbinding.ViewBinding

interface HasViewBinding<T : ViewBinding> {
    val binding: T
}
