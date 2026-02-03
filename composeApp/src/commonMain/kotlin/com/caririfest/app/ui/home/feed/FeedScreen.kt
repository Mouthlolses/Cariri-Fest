package com.caririfest.app.ui.home.feed

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import caririfest.composeapp.generated.resources.Res
import caririfest.composeapp.generated.resources.calendar
import caririfest.composeapp.generated.resources.image_break
import caririfest.composeapp.generated.resources.location_pin
import caririfest.composeapp.generated.resources.mascot_sf
import coil3.compose.SubcomposeAsyncImage
import com.caririfest.app.components.CategoryCard
import com.caririfest.app.components.EventCard
import com.caririfest.app.components.LoadingIndicatorLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen() {

    val viewModel: FeedViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val uiStateHotFilter = state.events.filter { it.hot }
    val pagerState = rememberPagerState(pageCount = { uiStateHotFilter.size })
    val scope = rememberCoroutineScope()

    LaunchedEffect(
        pagerState.isScrollInProgress
    ) {
        if (!pagerState.isScrollInProgress) {
            delay(6000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            scope.launch {
                pagerState.animateScrollToPage(
                    page = nextPage,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }
    }


    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingIndicatorLayout()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.mascot_sf),
                        contentDescription = "mascot"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        state.error!!,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "O que vai ser hoje?",
                                fontWeight = FontWeight.Bold
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,
                            scrolledContainerColor = Color.Transparent,
                            navigationIconContentColor = Color.Transparent,
                            titleContentColor = Color.White,
                            actionIconContentColor = Color.Transparent,
                            subtitleContentColor = Color.Black,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFFFEA00),
                                        Color(0xFFFF9800)
                                    )
                                )
                            )
                    )
                }
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFEFEFEF))
                        .padding(innerPadding)
                        .navigationBarsPadding(),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Imperdíveis",
                                fontWeight = FontWeight.Medium,
                                fontSize = 24.sp,
                                color = Color(0xFF1F2937),
                                modifier = Modifier
                                    .padding(start = 12.dp, top = 12.dp, bottom = 8.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(16.dp),
                                    clip = false
                                )
                                .background(Color.White)
                        ) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(366.dp)
                            ) { page ->
                                val event =
                                    uiStateHotFilter[page]
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable(
                                            onClick = {
//                                                navController.navigate("newsDetailsScreen/${event.id}")
                                            },
                                            enabled = true
                                        )
                                        .padding(
                                            start = 16.dp,
                                            end = 16.dp,
                                            top = 16.dp,
                                            bottom = 16.dp
                                        ),
                                ) {
                                    Column(
                                        Modifier
                                            .fillMaxWidth()
                                            .background(Color.White)
                                            .padding(bottom = 12.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        SubcomposeAsyncImage(
                                            model = event.img,
                                            contentDescription = "events",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(204.dp)
                                                .clip(RoundedCornerShape(26.dp)),
                                            contentScale = ContentScale.Crop,
                                            loading = {
                                                Box(
                                                    modifier = Modifier.fillMaxSize(),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    CircularProgressIndicator(
                                                        strokeWidth = 2.dp,
                                                        color = Color(0xFFFF9800)
                                                    )
                                                }
                                            },
                                            error = {
                                                Icon(
                                                    painter = painterResource(Res.drawable.image_break),
                                                    contentDescription = null
                                                )
                                            }
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(
                                            text = event.title,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            color = Color(0xFF333333),
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp)
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(Res.drawable.calendar),
                                                contentDescription = "date",
                                                tint = Color.DarkGray,
                                                modifier = Modifier.size(18.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = event.date,
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 14.sp,
                                                color = Color(0xFF666666),
                                                textAlign = TextAlign.Center,
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(Res.drawable.location_pin),
                                                contentDescription = "Place",
                                                tint = Color.Red,
                                                modifier = Modifier.size(18.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = event.location,
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 14.sp,
                                                color = Color(0xFF888888),
                                                textAlign = TextAlign.Center,
                                            )
                                        }
                                    }
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                repeat(uiStateHotFilter.size) { index ->
                                    val isSelected = pagerState.currentPage == index
                                    Box(
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .size(if (isSelected) 12.dp else 8.dp)
                                            .background(
                                                if (isSelected) Color(0xFFFF6D00) else Color.LightGray,
                                                shape = CircleShape
                                            )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(26.dp))
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp)
                                .padding(top = 18.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Categorias",
                                fontWeight = FontWeight.Medium,
                                fontSize = 22.sp,
                                color = Color(0xFF1F2937),
                                modifier = Modifier.weight(1f)
                            )
                            TextButton(
                                onClick = {},
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = "Ver tudo",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF2563EB)
                                )
                            }
                        }
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(categories) { value ->
                                CategoryCard(
                                    icon = value.image,
                                    title = value.nameCategories
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp)
                                .padding(top = 18.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Em Alta",
                                fontWeight = FontWeight.Medium,
                                fontSize = 22.sp,
                                color = Color(0xFF1F2937),
                                modifier = Modifier.weight(1f)
                            )
                            TextButton(
                                onClick = {},
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = "Ver tudo",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF2563EB)
                                )
                            }
                        }
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(state.events) { doc ->
                                EventCard(
                                    imageUrl = doc.img,
                                    title = doc.title,
                                    location = doc.location,
                                    date = doc.date,
                                    onClick = {
//                                        navController.navigate("newsDetailsScreen/${doc.id}")
                                    }
                                )
                            }
                        }
                    }
//                    item {
//                        if (recentEvents.isNotEmpty()) {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(horizontal = 18.dp)
//                                    .padding(top = 18.dp, bottom = 4.dp),
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Text(
//                                    text = "Visto recentemente",
//                                    fontFamily = permanentMarker,
//                                    fontWeight = FontWeight.Medium,
//                                    fontSize = 22.sp,
//                                    color = Color(0xFF1F2937),
//                                    modifier = Modifier.weight(1f)
//                                )
//                                TextButton(
//                                    onClick = { },
//                                    contentPadding = PaddingValues(0.dp)
//                                ) {
//                                    Text(
//                                        text = "Ver tudo",
//                                        fontSize = 14.sp,
//                                        fontWeight = FontWeight.Medium,
//                                        color = Color(0xFF2563EB)
//                                    )
//                                }
//                            }
//                            LazyRow(
//                                contentPadding = PaddingValues(horizontal = 16.dp),
//                                horizontalArrangement = Arrangement.spacedBy(16.dp)
//                            ) {
//                                items(recentEvents) { doc ->
//                                    EventCard(
//                                        imageUrl = doc.img,
//                                        title = doc.title,
//                                        location = doc.location,
//                                        date = doc.date,
//                                        onClick = {
//                                            navController.navigate("newsDetailsScreen/${doc.id}")
//                                        }
//                                    )
//                                }
//                            }
//                        }
//                    }
                }
            }
        }
    }
}