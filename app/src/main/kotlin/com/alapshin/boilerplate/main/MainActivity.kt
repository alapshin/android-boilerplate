package com.alapshin.boilerplate.main

import android.os.Bundle
import com.alapshin.boilerplate.base.InjectableActivity
import com.alapshin.boilerplate.base.viewBinding
import com.alapshin.boilerplate.databinding.MainActivityBinding

class MainActivity : InjectableActivity() {
    private val binding by viewBinding { MainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
