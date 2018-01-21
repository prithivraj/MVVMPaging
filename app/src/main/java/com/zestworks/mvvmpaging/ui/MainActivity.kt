package com.zestworks.mvvmpaging.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zestworks.mvvmpaging.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.parentPanel, MainFragment()).commitNow()
    }
}
