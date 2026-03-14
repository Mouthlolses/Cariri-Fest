package com.caririfest.app.ui.home.feed.seeAll

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.caririfest.app.navigation.home.HomeRoutes
import com.caririfest.app.repository.EventRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class SeeAllViewModel(
    repository: EventRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route =
        savedStateHandle.toRoute<HomeRoutes.SeeAllScreen>()

    val uiState: StateFlow<SeeAllUiState> =
        repository.getAllHandle(ids = route.events)
            .map { events ->
                if (events != null) {
                    SeeAllUiState.Success(events)
                } else {
                    SeeAllUiState.Error
                }
            }
            .onStart {
                emit(SeeAllUiState.Loading)
            }
            .catch {
                emit(SeeAllUiState.Error)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = SeeAllUiState.Loading
            )

}