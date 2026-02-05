package com.caririfest.app.ui.splash

import androidx.lifecycle.ViewModel
import com.caririfest.app.helper.OnboardingManager
import com.caririfest.app.navigation.root.RootRoutes

class SplashViewModel(
    private val onboardingManager: OnboardingManager
) : ViewModel() {

    fun decideRoute(): RootRoutes {
        return if (onboardingManager.hasSeenOnboarding()) {
            RootRoutes.Home
        } else {
            RootRoutes.OnBoarding
        }
    }
}