package com.caririfest.app.ui.home.feed.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.caririfest.app.model.Event
import com.caririfest.app.navigation.home.HomeRoutes
import com.caririfest.app.repository.EventRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class EventDetailViewModel(
    repository: EventRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route =
        savedStateHandle.toRoute<HomeRoutes.EventDetail>()

    val uiState: StateFlow<EventDetailUiState> =
        repository.getById(route.eventId)
            .map<Event?, EventDetailUiState> { event ->

                if (event != null) {
                    EventDetailUiState.Success(event)
                } else {
                    EventDetailUiState.Error
                }
            }
            .onStart {
                emit(EventDetailUiState.Loading)
            }
            .catch {
                emit(EventDetailUiState.Error)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                EventDetailUiState.Loading
            )
}