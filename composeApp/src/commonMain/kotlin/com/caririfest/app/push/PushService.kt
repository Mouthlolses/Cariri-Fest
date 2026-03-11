package com.caririfest.app.push

interface PushService {
    suspend fun requestPermission()
    fun getUserId(): String?
    fun addTag(key: String, value: String)
}