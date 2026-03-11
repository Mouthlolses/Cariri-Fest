package com.caririfest.app

import android.app.Application
import coil3.ImageLoader
import com.caririfest.app.di.initializeKoin
import com.caririfest.app.share.AppContextHolder
import com.caririfest.app.share.ImageLoaderHolder
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import org.koin.android.ext.koin.androidContext

class CaririFestApp : Application(){
    override fun onCreate() {
        super.onCreate()
        AppContextHolder.context = this
        ImageLoaderHolder.imageLoader = ImageLoader(this)

        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        OneSignal.initWithContext(this, "11e63d47-15ab-4daf-8bf9-2c15798ffb83")

        initializeKoin {
            androidContext(this@CaririFestApp)
        }
    }
}