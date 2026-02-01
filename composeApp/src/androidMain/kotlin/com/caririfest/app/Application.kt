package com.caririfest.app

import android.app.Application
import com.caririfest.app.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class Application : Application(){
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@Application)
        }
    }
}