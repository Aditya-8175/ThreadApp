package com.example.thread_clone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thread_clone.screens.BottomNav.BottomNav
import com.example.thread_clone.screens.Home.Home
import com.example.thread_clone.screens.Login.LoginScreen
import com.example.thread_clone.screens.Login.Register
import com.example.thread_clone.screens.Notification.Notification
import com.example.thread_clone.screens.Search.Search
import com.example.thread_clone.screens.Splash.SplashScreen
import com.example.thread_clone.screens.Thread.AddThread

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.SplashScreen.routes) {

        composable(Routes.SplashScreen.routes){
            SplashScreen(navController)
        }
        composable(Routes.Login.routes){
            LoginScreen(navController)
        }
        composable(Routes.Register.routes){
            Register(navController = navController)
        }

        composable(Routes.Home.routes){
            Home()
        }
        composable(Routes.Notification.routes){
            Notification()
        }
        composable(Routes.AddThread.routes){
            AddThread(navController)
        }

        composable(Routes.Search.routes){
            Search()
        }

        composable(Routes.Profile.routes){
            Register(navController = navController)
        }

        composable(Routes.BottomNav.routes){
            BottomNav(navController)
        }

    }

}