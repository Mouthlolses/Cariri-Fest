package com.caririfest.app.pushAndroid

import com.caririfest.app.push.PushService
import com.onesignal.OneSignal

class AndroidPushService : PushService {

    override suspend fun requestPermission() {
        if (!OneSignal.Notifications.permission) {
            OneSignal.Notifications.requestPermission(true)
        }
    }

    override fun getUserId(): String {
        return OneSignal.User.pushSubscription.id
    }

    override fun addTag(key: String, value: String) {
        OneSignal.User.addTag(key, value)
    }

}