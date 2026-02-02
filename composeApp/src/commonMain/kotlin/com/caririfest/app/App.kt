package com.caririfest.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caririfest.app.navigation.AppNavHost

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold { innerPadding ->
            val isIos = getPlatform().name.contains("iOS")
            val negativePadding = innerPadding.calculateTopPadding() - if (isIos) 8.dp else 0.dp
            val positivePadding = if (isIos) 56.dp else 28.dp

            var bgColor by remember { mutableStateOf(Color.White) }

            Box(
                Modifier
                    .background(bgColor)
                    .fillMaxSize()
                    .offset(y = negativePadding)
                    .padding(
                        top = positivePadding,
                        bottom = innerPadding.calculateBottomPadding(),
                        start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                        end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    )
            ) {
                AppNavHost()
            }
        }
    }
}