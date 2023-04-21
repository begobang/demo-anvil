package com.nttdata.demoanvil.di

import androidx.lifecycle.ViewModel
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.reflect.KClass

/* ----- Annotation Region ----- */

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ContributesViewModel(val viewModelClass: KClass<out ViewModel>)

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SingleIn(val clazz: KClass<*>)