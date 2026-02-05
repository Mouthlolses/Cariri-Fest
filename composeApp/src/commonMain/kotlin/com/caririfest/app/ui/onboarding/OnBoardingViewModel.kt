package com.caririfest.app.ui.onboarding

import androidx.lifecycle.ViewModel
import com.caririfest.app.helper.OnboardingManager

class OnBoardingViewModel(
    private val onboardingManager: OnboardingManager
) : ViewModel() {

    fun finishOnboarding() {
        onboardingManager.setSeen()
    }
}