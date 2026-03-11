package com.caririfest.app.ui.home.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caririfest.composeapp.generated.resources.Res
import caririfest.composeapp.generated.resources.caririfestlogo1
import co.touchlab.kermit.Logger
import com.caririfest.app.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource

class FeedViewModel(
    private val eventRepository: EventRepository,
    private val logger: Logger
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                eventRepository.refresh()
            } catch (e: Exception) {
                logger.d { e.message ?: "" }
            }
            _isRefreshing.value = false
        }
    }

    private val adsContent = "https://topsortassets.com/asset_01kewrgjyaepcrg8h7yj100vg3.png"
    private val defaultCategories = listOf(
        Categories(
            id = 1,
            image = Res.drawable.caririfestlogo1,
            nameCategories = "Cultura & Tradição"
        ),
        Categories(
            id = 2,
            image = Res.drawable.caririfestlogo1,
            nameCategories = "Música & Entretenimento"
        ),
        Categories(
            id = 3,
            image = Res.drawable.caririfestlogo1,
            nameCategories = "Gastronomia"
        ),
        Categories(
            id = 4,
            image = Res.drawable.caririfestlogo1,
            nameCategories = "Esporte & Bem-Estar"
        ),
        Categories(
            id = 5,
            image = Res.drawable.caririfestlogo1,
            nameCategories = "Educação & Negócios"
        ),
        Categories(
            id = 6,
            image = Res.drawable.caririfestlogo1,
            nameCategories = "Família & Comunidade"
        ),
        Categories(
            id = 7,
            image = Res.drawable.caririfestlogo1,
            nameCategories = "Cinema & Arte"
        ),
        Categories(
            id = 8,
            image = Res.drawable.caririfestlogo1,
            nameCategories = "Turismo & Natureza"
        )
    )

    val uiState = combine(
        eventRepository.getAll(),
        _isRefreshing
    ) { events, refreshing ->
        FeedUiState(
            events = events,
            adsCard = adsContent,
            categories = defaultCategories,
            isRefreshing = refreshing,
            isLoading = false
        )
    }
        .catch { e ->
            emit(
                FeedUiState(
                    error = "Error : ${e.message}",
                    isLoading = false
                )
            )
            logger.d { "${e.message}" }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            FeedUiState(isLoading = true)
        )
}


data class Categories(
    val id: Int,
    val image: DrawableResource,
    val nameCategories: String = ""
)