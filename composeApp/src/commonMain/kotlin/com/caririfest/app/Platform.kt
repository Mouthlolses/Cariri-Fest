package com.caririfest.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform