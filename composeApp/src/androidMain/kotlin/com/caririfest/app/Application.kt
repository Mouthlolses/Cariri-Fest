package com.caririfest.app

import android.app.Application
import coil3.ImageLoader
import com.caririfest.app.di.initializeKoin
import com.caririfest.app.share.AppContextHolder
import com.caririfest.app.share.ImageLoaderHolder
import org.koin.android.ext.koin.androidContext

class CaririFestApp : Application(){
    override fun onCreate() {
        super.onCreate()
        AppContextHolder.context = this
        ImageLoaderHolder.imageLoader = ImageLoader(this)
        initializeKoin {
            androidContext(this@CaririFestApp)
        }
    }
}