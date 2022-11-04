package com.kkunan.kmm.randomdog.di

import android.content.Context

interface ContextStorage {
    companion object {
        lateinit var context: Context
    }
}

