package com.caririfest.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.caririfest.app.ui.onboarding.OnBoardingScreen
import com.caririfest.app.ui.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    onChangeColor: (Long) -> Unit = {}
) {

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {

        composable<Routes.Splash> {
            SplashScreen(
                onNavigate = {
                    navController.navigate(Routes.OnBoarding) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.OnBoarding> {
            OnBoardingScreen(
                onFinish = {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.OnBoarding) { inclusive = true }
                    }
                }
            )
        }
//
//        composable<Routes.Home> {
//            HomeScreen(navController)
//        }
//
//        composable<Routes.Search> {
//            SearchScreen()
//        }
//
//        composable<Routes.Maps> {
//            MapsScreen()
//        }
//
//        composable<Routes.Feed> {
//            FeedScreen()
//        }
//
//        composable<Routes.EventDetail> { backStackEntry ->
//
//            val args = backStackEntry.toRoute<Routes.EventDetail>()
//
//            EventDetailScreen(
//                eventId = args.eventId
//            )
//        }
    }
}