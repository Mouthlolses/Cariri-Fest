package com.caririfest.app.share

import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

actual fun shareEvent(
    imageUrl: String,
    text: String
) {

    val context = AppContextHolder.context

    val loader = ImageLoaderHolder.imageLoader

    CoroutineScope(Dispatchers.IO).launch {

        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false)
            .build()

        val result = loader.execute(request)

        if (result is SuccessResult) {

            val bitmap = result.image.toBitmap()

            withContext(Dispatchers.Main) {
                shareContent(context, bitmap, text)
            }
        }
    }
}