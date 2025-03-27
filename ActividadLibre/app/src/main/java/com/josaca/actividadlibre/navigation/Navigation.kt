package com.josaca.actividadlibre.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.josaca.actividadlibre.views.FirstScreen
import com.josaca.actividadlibre.views.SecondScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.FirstScreen.route
    ) {
        composable(
            route = Routes.FirstScreen.route
        ) {
            FirstScreen(navController)
        }

        composable(
            route = Routes.SecondScreen.route,
            arguments = listOf(
                navArgument("peso") {
                    type = NavType.IntType
                },
                navArgument("altura") {
                    type = NavType.IntType
                },
                navArgument("unidad") {
                    type = NavType.StringType
                }
            )
        ) {
            val peso = it.arguments?.getInt("peso")
            val altura = it.arguments?.getInt("altura")
            val unidad = it.arguments?.getString("unidad")
            requireNotNull(peso)
            requireNotNull(altura)
            requireNotNull(unidad)
            SecondScreen(navController, peso, altura, unidad)
        }
    }
}