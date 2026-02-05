package com.caririfest.app.wrapper

import com.russhwolf.settings.Settings

class AppSettings(
    private val settings: Settings
) {

    var hasSeenOnboarding: Boolean
        get() = settings.getBoolean("onboarding", false)
        set(value) = settings.putBoolean("onboarding", value)
}