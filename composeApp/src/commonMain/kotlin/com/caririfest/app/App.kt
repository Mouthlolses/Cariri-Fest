package com.caririfest.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.caririfest.app.navigation.root.AppNavHost

@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(
            Modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) {
            AppNavHost()
        }
    }
}
