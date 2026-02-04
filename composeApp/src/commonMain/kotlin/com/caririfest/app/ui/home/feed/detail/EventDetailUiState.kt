package com.caririfest.app.ui.home.feed.detail

import com.caririfest.app.model.Event

sealed interface EventDetailUiState {
    object Loading : EventDetailUiState
    data class Success(val event: Event) : EventDetailUiState
    object Error : EventDetailUiState
}