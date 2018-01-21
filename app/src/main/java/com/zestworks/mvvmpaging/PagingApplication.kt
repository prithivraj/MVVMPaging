package com.zestworks.mvvmpaging

import android.app.Application
import com.zestworks.mvvmpaging.di.AppComponentProvider


class PagingApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        AppComponentProvider.instance.initialize(applicationContext)
    }
}