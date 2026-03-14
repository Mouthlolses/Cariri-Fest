package com.caririfest.app.ui.home.search

import com.caririfest.app.model.Event

data class SearchUiState(
    val query: String = "",
    val events: List<Event> = emptyList(),
    val isLoading: Boolean = false
)