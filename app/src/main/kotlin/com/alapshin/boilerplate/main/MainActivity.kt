package com.alapshin.boilerplate.main

import com.alapshin.boilerplate.base.BaseMviActivity
import com.alapshin.boilerplate.base.viewBinding
import com.alapshin.boilerplate.databinding.MainActivityBinding
import com.alapshin.mvi.MviState

class MainActivity : BaseMviActivity<MainActivityBinding, MviState>() {
    override val binding by viewBinding { MainActivityBinding.inflate(layoutInflater) }

    override fun render(state: MviState) {
    }
}
