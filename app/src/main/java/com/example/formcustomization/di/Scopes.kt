package com.example.formcustomization.di

import javax.inject.Scope
import kotlin.annotation.AnnotationTarget.*

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScoped

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScoped

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(
    TYPE,
    FUNCTION,
    CLASS
)
annotation class FragmentScoped