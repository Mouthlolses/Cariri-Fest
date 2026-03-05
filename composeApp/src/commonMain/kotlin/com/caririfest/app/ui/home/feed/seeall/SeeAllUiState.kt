package com.caririfest.app.ui.home.feed.seeall

import com.caririfest.app.model.Event

sealed interface SeeAllUiState {
    object Loading : SeeAllUiState
    data class Success(val event: List<Event>) : SeeAllUiState
    object Error : SeeAllUiState
}
