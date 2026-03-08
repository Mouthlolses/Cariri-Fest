package com.caririfest.app.share

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

fun shareContent(
    context: Context,
    bitmap: Bitmap,
    text: String
) {

    val cachePath = File(context.cacheDir, "images")
    cachePath.mkdirs()

    val file = File(cachePath, "shared_image.png")

    FileOutputStream(file).use { stream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    }

    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "image/*"
        putExtra(Intent.EXTRA_STREAM, uri)
        putExtra(Intent.EXTRA_TEXT, text)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(
        Intent.createChooser(intent, "Compartilhar evento")
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    )
}