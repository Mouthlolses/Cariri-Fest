package com.caririfest.app.share

import android.annotation.SuppressLint
import android.content.Context
import coil3.ImageLoader

@SuppressLint("StaticFieldLeak")
object AppContextHolder {
    lateinit var context: Context
}

object ImageLoaderHolder {
    lateinit var imageLoader: ImageLoader
}