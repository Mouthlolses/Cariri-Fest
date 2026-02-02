package com.caririfest.app.navigation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.caririfest.app.ui.home.HomeScreen
import com.caririfest.app.ui.onboarding.OnBoardingScreen
import com.caririfest.app.ui.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    onChangeColor: (Long) -> Unit = {}
) {

    NavHost(
        navController = navController,
        startDestination = RootRoutes.Splash
    ) {

        composable<RootRoutes.Splash> {
            SplashScreen(
                onNavigate = {
                    navController.navigate(RootRoutes.OnBoarding) {
                        popUpTo(RootRoutes.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<RootRoutes.OnBoarding> {
            OnBoardingScreen(
                onFinish = {
                    navController.navigate(RootRoutes.Home) {
                        popUpTo(RootRoutes.OnBoarding) { inclusive = true }
                    }
                }
            )
        }

        composable<RootRoutes.Home> {
            HomeScreen(navController)
        }
    }
}