package com.example.thread_clone.navigation

sealed class Routes (val routes: String) {

    object Home : Routes("home")
    object SplashScreen : Routes("splash")
    object Login : Routes("login")
    object Profile : Routes("profile")
    object AddThread : Routes("add_thread")
    object Search : Routes("search")
    object Notification: Routes("notification")
    object BottomNav : Routes("bottom_nav")
    object Register : Routes("register")

}