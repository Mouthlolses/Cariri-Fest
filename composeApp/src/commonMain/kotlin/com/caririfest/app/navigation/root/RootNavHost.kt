package com.caririfest.app.navigation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.caririfest.app.navigation.RootViewModel
import com.caririfest.app.navigation.StartDestination
import com.caririfest.app.ui.home.HomeScreen
import com.caririfest.app.ui.onboarding.OnBoardingScreen
import com.caririfest.app.ui.splash.SplashScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost() {

    val rootViewModel: RootViewModel = koinViewModel()

    when(val destination = rootViewModel.startDestination) {

        StartDestination.Loading -> {
            // Splash REAL (só UI)
            SplashScreen()
        }

        else -> {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = destination.toRoute()
            ) {

                composable<RootRoutes.OnBoarding> {
                    OnBoardingScreen(
                        onFinish = {
                            navController.navigate(RootRoutes.Home) {
                                popUpTo(0)
                            }
                        }
                    )
                }

                composable<RootRoutes.Home> {
                    HomeScreen(navController)
                }
            }
        }
    }
}

fun StartDestination.toRoute(): Any {
    return when(this) {
        StartDestination.Home -> RootRoutes.Home
        StartDestination.OnBoarding -> RootRoutes.OnBoarding
        else -> RootRoutes.Home
    }
}