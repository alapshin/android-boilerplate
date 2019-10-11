package com.alapshin.boilerplate.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <V : ViewBinding> Fragment.viewBinding(initializer: () -> V)
    : ReadOnlyProperty<Fragment, V> = LifecycleAwareLazy(initializer)
fun <V : ViewBinding> AppCompatActivity.viewBinding(initializer: () -> V)
    : ReadOnlyProperty<Any, V> = LifecycleAwareLazy(initializer)

/**
 * Lifecycle aware implementation of [Lazy]
 */
private class LifecycleAwareLazy<T, V>(private val initializer: () -> V)
    : ReadOnlyProperty<T, V>, LifecycleObserver {
    private var value: V? = null
    private var attachedToLifecycleOwner = false

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        checkAddToLifecycleOwner(thisRef)
        if (value == null) {
            value = initializer()
        }
        @Suppress("UNCHECKED_CAST")
        return value as V
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun destroy() {
        value = null
    }

    private fun checkAddToLifecycleOwner(thisRef: T) {
        if (!attachedToLifecycleOwner) {
            attachedToLifecycleOwner = true
            if (thisRef is AppCompatActivity) {
                thisRef.lifecycle.addObserver(this)
            } else if (thisRef is Fragment) {
                thisRef.viewLifecycleOwner.lifecycle.addObserver(this)
            }
        }
    }
}