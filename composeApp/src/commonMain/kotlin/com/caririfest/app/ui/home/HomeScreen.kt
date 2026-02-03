package com.caririfest.app.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.caririfest.app.navigation.home.BottomNavigationBar
import com.caririfest.app.navigation.home.HomeNavHost
import com.caririfest.app.navigation.home.HomeRoutes


@Composable
fun HomeScreen(
    rootNavController: NavHostController
) {

    val homeNavController = rememberNavController()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()

    val bottomBarRoutes = setOf(
        HomeRoutes.Feed::class,
        HomeRoutes.Search::class,
        HomeRoutes.Maps::class,
        HomeRoutes.Radar::class
    )

    val shouldShowBottomBar =
        navBackStackEntry
            ?.destination
            ?.hierarchy
            ?.any { destination ->
                bottomBarRoutes.any { route ->
                    destination.hasRoute(route)
                }
            } == true

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        HomeNavHost(
            rootNavController = rootNavController,
            homeNavController = homeNavController
        )

        if (shouldShowBottomBar) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp)
                    .navigationBarsPadding()
            ) {
                BottomNavigationBar(navController = homeNavController)
            }
        }
    }
}