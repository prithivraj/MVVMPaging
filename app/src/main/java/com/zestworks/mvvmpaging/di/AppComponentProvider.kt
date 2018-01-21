package com.zestworks.mvvmpaging.di

import android.content.Context


class AppComponentProvider {
    companion object {
        val instance = AppComponentProvider()
    }

    lateinit var appComponentInstance: AppComponent

    fun initialize(applicationContext: Context): AppComponent {
        appComponentInstance = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
        return appComponentInstance
    }
}
