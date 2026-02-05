package com.caririfest.app.helper

import com.russhwolf.settings.Settings

class OnboardingManager(
    private val settings: Settings
) {
    companion object {
        private const val KEY = "has_seen_onboarding"
    }

    fun hasSeenOnboarding(): Boolean {
        return settings.getBoolean(KEY, false)
    }

    fun setSeen() {
        settings.putBoolean(KEY, true)
    }
}