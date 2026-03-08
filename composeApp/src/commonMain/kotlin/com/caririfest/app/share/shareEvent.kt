package com.caririfest.app.share

import com.caririfest.app.model.Event

expect fun shareEvent(
    imageUrl: String,
    text: String
)

fun buildShareText(event: Event): String {
    return buildString {
        appendLine("🎉 ${event.title}")
        appendLine()
        appendLine("📍 Local: ${event.location}")
        appendLine()
        appendLine("📅 Data: ${event.date}")
        appendLine()
        appendLine("📲 Descubra mais eventos no Cariri com o app Cariri Fest!")
        appendLine()
        appendLine(
            "👉 Baixe grátis: https://play.google.com/store/apps/details?id=com.caririfest.app_jnproject"
        )
    }
}