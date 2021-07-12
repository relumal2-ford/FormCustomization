package com.example.formcustomization.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.formcustomization.activity.LoginActivity
import com.example.formcustomization.activity.RegistrationActivity
import com.example.formcustomization.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector()
    internal abstract fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector()
    internal abstract fun registrationActivity(): RegistrationActivity
}

@Module
abstract class ViewModelModule {
    @Binds
    @ApplicationScoped
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}