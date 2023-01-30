package com.example.kc

import android.app.Application

class KCApp : Application() {

    companion object {
        lateinit var appContext: KCApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initContext()
    }

    private fun initContext() {
        appContext = this
    }
}