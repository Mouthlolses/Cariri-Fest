package com.caririfest.app.ui.home.feed.seeall

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caririfest.app.ui.components.EventCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SeeAllScreen(
    onEventClick: (String) -> Unit
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
                CircularProgressIndicator()
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ){
                items((state as SeeAllUiState.Success).event){ doc ->
                    EventCard(
                        imageUrl = doc.img,
                        title = doc.title,
                        location = doc.location,
                        date = doc.date,
                        onClick = {
                            onEventClick(doc.id)
                        }
                    )
                }
            }
        }
    }
}