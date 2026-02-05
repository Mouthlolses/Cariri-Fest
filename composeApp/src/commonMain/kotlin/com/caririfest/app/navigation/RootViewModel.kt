package com.caririfest.app.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caririfest.app.helper.OnboardingManager
import com.caririfest.app.repository.EventRepository
import kotlinx.coroutines.launch

class RootViewModel(
    private val onboardingManager: OnboardingManager,
    private val eventRepository: EventRepository
) : ViewModel() {

    var startDestination by mutableStateOf<StartDestination>(
        StartDestination.Loading
    )
        private set

    init {
        viewModelScope.launch {

            if (onboardingManager.hasSeenOnboarding()) {

                // 🔥 prefetch
                eventRepository.refresh()

                startDestination = StartDestination.Home
            } else {
                startDestination = StartDestination.OnBoarding
            }
        }
    }
}