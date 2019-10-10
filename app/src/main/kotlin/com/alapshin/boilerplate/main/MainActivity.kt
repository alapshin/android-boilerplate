package com.alapshin.boilerplate.main

import android.os.Bundle
import android.os.PersistableBundle
import com.alapshin.boilerplate.R
import com.alapshin.boilerplate.base.BaseMviActivity
import com.alapshin.boilerplate.log.LogUtil
import com.alapshin.mvi.MviState

class MainActivity : BaseMviActivity<MviState>() {
    override val layoutRes = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        LogUtil.d("AAAA")
    }

    override fun render(state: MviState) {
    }
}
