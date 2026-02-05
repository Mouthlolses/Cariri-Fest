package com.caririfest.app.navigation

sealed interface StartDestination {

    data object OnBoarding : StartDestination
    data object Home : StartDestination
    data object Loading : StartDestination
}