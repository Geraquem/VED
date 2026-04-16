package com.mmfsin.ved.presentation.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.ved.presentation.dilemmas.DilemmasScreen
import com.mmfsin.ved.presentation.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                navToDilemmas = { navController.navigate(Dilemmas) }
            )
        }

        composable<Dilemmas> {
            DilemmasScreen()
        }
    }
}