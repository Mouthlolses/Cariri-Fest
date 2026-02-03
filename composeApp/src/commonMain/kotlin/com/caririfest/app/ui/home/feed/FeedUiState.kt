package com.caririfest.app.ui.home.feed

import com.caririfest.app.model.Event

data class FeedUiState(
    val events: List<Event> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)