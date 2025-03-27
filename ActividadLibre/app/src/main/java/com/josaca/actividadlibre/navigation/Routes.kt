package com.josaca.actividadlibre.navigation

sealed class Routes(val route: String) {
    object FirstScreen : Routes("first_screen")

    object SecondScreen : Routes("second_screen/{peso}/{altura}/{unidad}") {
        fun createRoute(peso: Int, altura: Int, unidad: String) =
            "second_screen/$peso/$altura/$unidad"
    }
}