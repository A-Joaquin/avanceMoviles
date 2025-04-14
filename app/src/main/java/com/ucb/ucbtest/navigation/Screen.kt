package com.ucb.ucbtest.navigation

sealed class Screen(val route: String) {
    object GitaliasScreen : Screen("gitlab")
    object TakePhotoScreen : Screen("takephoto")
    object MenuScreen : Screen("menu")
    object LoginScreen : Screen("login")
    object MoviesScreen : Screen("movies")
    object MovieDetailScreen : Screen("movieDetail")
    object CounterScreen : Screen("counter")
    object MarsHomeScreen : Screen("mars_home")
    object MarsGalleryScreen : Screen("mars_gallery/{camera}") {
        fun createRoute(camera: String?) = "mars_gallery/${camera ?: "all"}"
    }
}
