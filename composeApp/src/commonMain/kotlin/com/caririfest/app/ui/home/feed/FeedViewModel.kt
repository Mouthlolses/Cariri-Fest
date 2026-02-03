package com.caririfest.app.ui.home.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caririfest.composeapp.generated.resources.Res
import caririfest.composeapp.generated.resources.caririfestlogo1
import com.caririfest.app.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jetbrains.compose.resources.DrawableResource

class FeedViewModel(
    eventRepository: EventRepository
) : ViewModel() {

    val uiState = eventRepository.getAll()
        .map { events ->
            FeedUiState(events = events)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            FeedUiState(isLoading = true)
        )


    private val _categories = MutableStateFlow<List<Categories>>(emptyList())
    val categories: StateFlow<List<Categories>> = _categories.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = listOf(
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
    }
}

data class Categories(
    val id: Int,
    val image: DrawableResource,
    val nameCategories: String = ""
)