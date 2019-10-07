package com.alapshin.boilerplate.di.scopes

import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
annotation class ScopeIn(val value: KClass<*>)
