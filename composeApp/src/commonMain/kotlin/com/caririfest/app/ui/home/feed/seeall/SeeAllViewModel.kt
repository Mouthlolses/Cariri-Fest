package com.caririfest.app.ui.home.feed.seeall

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.caririfest.app.navigation.home.HomeRoutes

class SeeAllViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route =
        savedStateHandle.toRoute<HomeRoutes.SeeAllScreen>()


}