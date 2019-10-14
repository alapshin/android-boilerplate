package com.alapshin.boilerplate.base

import androidx.viewbinding.ViewBinding

interface HasViewBinding<VB : ViewBinding> {
    val binding: VB
}
