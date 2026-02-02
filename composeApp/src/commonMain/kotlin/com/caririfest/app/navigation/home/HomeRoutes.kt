package com.caririfest.app.navigation.home

import kotlinx.serialization.Serializable

sealed interface HomeRoutes {

    @Serializable
    data object Feed : HomeRoutes

    @Serializable
    data object Search : HomeRoutes

    @Serializable
    data object Maps : HomeRoutes

    @Serializable
    data object Radar : HomeRoutes

    @Serializable
    data class EventDetail(
        val eventId: String
    ) : HomeRoutes
}