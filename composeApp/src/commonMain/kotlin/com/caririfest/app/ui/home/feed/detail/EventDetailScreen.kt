package com.caririfest.app.ui.home.feed.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import caririfest.composeapp.generated.resources.Res
import caririfest.composeapp.generated.resources.calendar
import caririfest.composeapp.generated.resources.caririfestlogo1
import caririfest.composeapp.generated.resources.ic_loupe
import caririfest.composeapp.generated.resources.left_arrow
import caririfest.composeapp.generated.resources.location_pin
import caririfest.composeapp.generated.resources.right_arrow
import coil3.compose.AsyncImage
import com.caririfest.app.components.BuyTicketBottomBar
import com.caririfest.app.components.ShareButton
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(
    onBackStackEntry: () -> Boolean
) {

    val viewModel: EventDetailViewModel = koinViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    when (uiState) {
        EventDetailUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    strokeWidth = 3.dp
                )
            }
        }

        EventDetailUiState.Error -> {
            Text("Error")
        }

        is EventDetailUiState.Success -> {
            Scaffold(
                topBar = {
                    Surface(
                        tonalElevation = 6.dp,
                        shadowElevation = 6.dp,
                        color = Color.White,
                        modifier = Modifier
                            .drawBehind {
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color.LightGray,
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            }
                    ) {
                        CenterAlignedTopAppBar(
                            title = {
                                Image(
                                    painter = painterResource(Res.drawable.caririfestlogo1),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(100.dp)
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { onBackStackEntry() }) {
                                    Icon(
                                        painter = painterResource(Res.drawable.left_arrow),
                                        contentDescription = "back",
                                        modifier = Modifier
                                            .size(26.dp),
                                        tint = Color(0xFFFF5733)
                                    )
                                }
                            },
                            modifier = Modifier
                                .height(96.dp)
                        )
                    }
                },
                bottomBar = {
                    BuyTicketBottomBar(
                        onClick = {
//                    val intent = Intent(Intent.ACTION_VIEW, event.fields.link.stringValue.toUri())
//                    context.startActivity(intent)
                        },
//                enable = event.fields.favorite.booleanValue
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .verticalScroll(scrollState)
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = (uiState as EventDetailUiState.Success).event.img,
                            contentDescription = "image_screen",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp),
                            contentScale = ContentScale.Crop
                        )

                        ShareButton(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .offset(y = 24.dp),
                            onClick = {
                                scope.launch {
                                    val text = buildString {
                                        appendLine("🎉 ${(uiState as EventDetailUiState.Success).event.title}")
                                        appendLine()
                                        appendLine("📍Local: ${(uiState as EventDetailUiState.Success).event.location}")
                                        appendLine()
                                        appendLine("📅 Data: ${(uiState as EventDetailUiState.Success).event.date}")
                                        appendLine()
                                        appendLine("📲 Descubra mais eventos no Cariri com o app Cariri Fest!")
                                        appendLine()
                                        appendLine(
                                            "👉 Disponível na Google Play " +
                                                    " Baixe grátis: https://play.google.com/store/apps/details?id=com.caririfest.app_jnproject"
                                        )
                                    }
//
//                                    val request = ImageRequest.Builder(context)
//                                        .data(event.fields.img.stringValue)
//                                        .allowHardware(false)
//                                        .build()
//
//                                    val result = withContext(Dispatchers.IO) {
//                                        imageLoader.execute(request)
//                                    }
//
//                                    val drawable = result.drawable
//                                    val bitmap = (drawable as BitmapDrawable).bitmap
//
//                                    withContext(Dispatchers.Main) {
//                                        shareContent(context, bitmap, text)
//                                    }

                                }
                            }
                        )
                    }

                    Column(modifier = Modifier.padding(top = 35.dp)) {
                        Text(
                            text = (uiState as EventDetailUiState.Success).event.title,
                            style = typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.calendar),
                                contentDescription = "location",
                                tint = Color.DarkGray,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = (uiState as EventDetailUiState.Success).event.date,
                                style = typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_loupe),
                                contentDescription = "location",
                                tint = Color.DarkGray,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = (uiState as EventDetailUiState.Success).event.time,
                                style = typography.bodyMedium,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.location_pin),
                                contentDescription = "location",
                                tint = Color.Red,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = (uiState as EventDetailUiState.Success).event.place,
                                style = typography.bodyMedium,
                                color = Color.Gray,
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.location_pin),
                                contentDescription = "location",
                                tint = Color.Red,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = (uiState as EventDetailUiState.Success).event.location,
                                style = typography.bodyMedium,
                                color = Color.Gray,
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(6.dp))

//                        LazyRow(
//                            state = listState,
//                            modifier = Modifier
//                                .fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically,
//                            userScrollEnabled = false
//                        ) {
//                            items(tagList) { isFavorite ->
//                                Tag(
//                                    text = if (isFavorite)
//                                        stringResource(R.string.available)
//                                    else
//                                        stringResource(R.string.exhausted),
//                                    backgroundColor = if (isFavorite) Color(0xFF4CAF50) else Color(
//                                        0xFF9E9E9E
//                                    ),
//                                    modifier = Modifier.padding(start = 16.dp)
//                                )
//                            }
//                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = (uiState as EventDetailUiState.Success).event.desc,
                            style = typography.bodyLarge,
                            color = Color.DarkGray,
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}