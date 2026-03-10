package com.caririfest.app.ui.home.feed.seeall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import caririfest.composeapp.generated.resources.Res
import caririfest.composeapp.generated.resources.left_arrow
import com.caririfest.app.ui.components.EventListItem
import com.caririfest.app.ui.components.LoadingIndicatorLayout
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeAllScreen(
    onEventClick: (String) -> Unit,
    onBackStackEntry: () -> Boolean
) {

    val viewmodel: SeeAllViewModel = koinViewModel()

    val state by viewmodel.uiState.collectAsStateWithLifecycle()


    when (state) {

        SeeAllUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingIndicatorLayout()
            }
        }

        SeeAllUiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error")
            }
        }

        is SeeAllUiState.Success -> {
            val events = (state as SeeAllUiState.Success).event

            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(onClick = { onBackStackEntry() }) {
                                Icon(
                                    painter = painterResource(Res.drawable.left_arrow),
                                    contentDescription = "back",
                                    modifier = Modifier.size(26.dp),
                                    tint = Color(0xFFFF5733)
                                )
                            }
                        }
                    )
                }
            ) { padding ->

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .navigationBarsPadding(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 70.dp)
                ) {

                    items(events) { event ->
                        EventListItem(
                            event = event,
                            onClick = { onEventClick(event.id) }
                        )
                    }
                }
            }
        }
    }
}