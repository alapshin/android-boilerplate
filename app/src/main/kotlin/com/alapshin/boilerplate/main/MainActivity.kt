package com.alapshin.boilerplate.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alapshin.boilerplate.base.viewBinding
import com.alapshin.boilerplate.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding { MainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
