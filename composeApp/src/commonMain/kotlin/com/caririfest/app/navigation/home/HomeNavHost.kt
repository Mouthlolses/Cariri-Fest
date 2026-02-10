package com.caririfest.app.navigation.home

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import caririfest.composeapp.generated.resources.Res
import caririfest.composeapp.generated.resources.ic_loupe
import caririfest.composeapp.generated.resources.icon_home
import caririfest.composeapp.generated.resources.icon_maps
import caririfest.composeapp.generated.resources.icon_world
import com.caririfest.app.ui.home.feed.FeedScreen
import com.caririfest.app.ui.home.feed.detail.EventDetailScreen
import com.caririfest.app.ui.home.feed.seeall.SeeAllScreen
import com.caririfest.app.ui.home.map.MapsScreen
import com.caririfest.app.ui.home.radar.RadarScreen
import com.caririfest.app.ui.home.search.SearchScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeNavHost(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = homeNavController,
        startDestination = HomeRoutes.Feed,
        modifier = modifier,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(400)
            ) + fadeIn(animationSpec = tween(400))
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(400)
            ) + fadeOut(animationSpec = tween(400))
        }
    ) {

        composable<HomeRoutes.Feed> {
            FeedScreen(
                onEventClick = { eventId ->
                    homeNavController.navigate(
                        HomeRoutes.EventDetail(eventId)
                    )
                }
            )
        }

        composable<HomeRoutes.Search> {
            SearchScreen()
        }

        composable<HomeRoutes.Maps> {
            MapsScreen()
        }

        composable<HomeRoutes.Radar> {
            RadarScreen()
        }

        composable<HomeRoutes.SeeAllScreen> {
            SeeAllScreen()
        }

        composable<HomeRoutes.EventDetail> {
            EventDetailScreen(
                onBackStackEntry = {
                    homeNavController.popBackStack()
                }
            )
        }
    }
}


data class BottomNavItem(
    val route: HomeRoutes,
    val label: String,
    val icon: DrawableResource
)

enum class NavItems(
    val title: String,
    val icon: DrawableResource,
    val route: HomeRoutes
) {
    FEED("Feed", Res.drawable.icon_home, HomeRoutes.Feed),
    SEARCH("Search", Res.drawable.ic_loupe, HomeRoutes.Search),
    MAP("Mapa", Res.drawable.icon_maps, HomeRoutes.Maps),
    RADAR("Radar", Res.drawable.icon_world, HomeRoutes.Radar)

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem(
            NavItems.FEED.route,
            NavItems.FEED.title,
            NavItems.FEED.icon
        ),
        BottomNavItem(
            NavItems.SEARCH.route,
            NavItems.SEARCH.title,
            NavItems.SEARCH.icon
        ),
        BottomNavItem(
            NavItems.MAP.route,
            NavItems.MAP.title,
            NavItems.MAP.icon
        ),
        BottomNavItem(
            NavItems.RADAR.route,
            NavItems.RADAR.title,
            NavItems.RADAR.icon
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        NavigationBar(
            modifier = Modifier
                .height(72.dp)
                .clip(RoundedCornerShape(36.dp))
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(36.dp),
                    spotColor = Color.Black.copy(alpha = 0.6f)
                )
                .background(Color(0xFF2B2939).copy(alpha = 0.95f))
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.08f),
                    shape = RoundedCornerShape(36.dp)
                ),
            containerColor = Color.Transparent,
            tonalElevation = 0.dp
        ) {

            items.forEach { item ->

                val selected = navBackStackEntry
                    ?.destination
                    ?.hierarchy
                    ?.any { destination ->
                        destination.route == item.route::class.qualifiedName
                    } == true

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.label,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    label = {}, // igual ao layout novo (sem texto)
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.4f),
                        indicatorColor = Color(0xFFFF9800)
                    )
                )
            }
        }
    }
}