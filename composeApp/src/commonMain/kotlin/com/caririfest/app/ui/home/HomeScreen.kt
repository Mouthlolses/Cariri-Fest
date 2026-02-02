package com.caririfest.app.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.caririfest.app.navigation.home.BottomNavigationBar
import com.caririfest.app.navigation.home.HomeNavHost


@Composable
fun HomeScreen(
    rootNavController: NavHostController
) {

    val homeNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = homeNavController)
        }
    ) { padding ->

        HomeNavHost(
            rootNavController = rootNavController,
            homeNavController = homeNavController,
            modifier = Modifier.padding(padding)
        )
    }
}