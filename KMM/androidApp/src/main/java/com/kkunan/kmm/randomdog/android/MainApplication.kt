package com.kkunan.kmm.randomdog.android

import android.app.Application
import android.content.Context
import com.kkunan.kmm.randomdog.di.ContextStorage
import com.kkunan.kmm.randomdog.di.initKoin

class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        val context: Context = applicationContext()
        ContextStorage.context = context
    }
}