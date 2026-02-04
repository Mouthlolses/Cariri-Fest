package com.caririfest.app.ui.onboarding

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import caririfest.composeapp.generated.resources.Res
import caririfest.composeapp.generated.resources.background_claro
import caririfest.composeapp.generated.resources.background_person
import caririfest.composeapp.generated.resources.background_yellow
import caririfest.composeapp.generated.resources.person_11
import caririfest.composeapp.generated.resources.person_2
import caririfest.composeapp.generated.resources.person_3
import com.caririfest.app.components.ButtonAllCustomized
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnBoardingScreen(
    onFinish: () -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            delay(4000)

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFEA00))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { pager ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (pager) {
                    0 -> OnBoardingScreeLayout(
                        image = Res.drawable.background_person,
                        image2 = Res.drawable.person_2,
                        title = "Descubra tudo o que está rolando perto de você",
                    )

                    1 -> OnBoardingScreeLayout(
                        image = Res.drawable.background_yellow,
                        image2 = Res.drawable.person_3,
                        title = "Sua agenda de eventos do Cariri, em um só lugar",
                    )

                    2 -> OnBoardingScreeLayout(
                        image = Res.drawable.background_claro,
                        image2 = Res.drawable.person_11,
                        title = "Conheça os lugares que contam a história da nossa região",
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        ) {
            repeat(3) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (isSelected) 12.dp else 8.dp)
                        .background(
                            if (isSelected) Color.Black else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
        ) {
            ButtonAllCustomized(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(start = 14.dp, end = 14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6D00),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(4.dp),
                onClick = {
                    onFinish()
                },
                text = "Vamos lá"
            )
        }
    }
}


@Composable
fun OnBoardingScreeLayout(
    image: DrawableResource,
    image2: DrawableResource,
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .padding(top = 12.dp, bottom = 150.dp)
                .offset(y = (-1).dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                Color(0xFFFDF4EE)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.35f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = "image_backgroundOnboarding",
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(image2),
                        contentDescription = "image_forOnboarding",
                        modifier = Modifier
                            .padding(top = 100.dp)
                            .width(200.dp)
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}