package com.caririfest.app.navigation

import kotlinx.serialization.Serializable


sealed class Routes {


    @Serializable
    data object Splash : Routes()


    @Serializable
    data object OnBoarding : Routes()


    @Serializable
    data object Home : Routes()

    @Serializable
    data object Search : Routes()

    @Serializable
    data object Maps : Routes()

    @Serializable
    data object Feed : Routes()

    @Serializable
    data class EventDetail(
        val eventId: String
    ) : Routes()

}