package com.alapshin.boilerplate.base

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A [Fragment] that injects its members in [onAttach] and can be used to inject child [Fragment]s attached to it.
 * Note that when this fragment gets reattached, its members will be injected again.
 */
abstract class InjectableFragment : Fragment(), HasAndroidInjector {
    @Inject
    protected lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun androidInjector() = androidInjector
}
