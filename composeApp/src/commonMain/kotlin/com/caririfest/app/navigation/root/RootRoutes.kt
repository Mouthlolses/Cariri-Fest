package com.caririfest.app.navigation.root

import kotlinx.serialization.Serializable

sealed interface RootRoutes {


    @Serializable
    data object Splash : RootRoutes


    @Serializable
    data object OnBoarding : RootRoutes


    @Serializable
    data object Home : RootRoutes

}