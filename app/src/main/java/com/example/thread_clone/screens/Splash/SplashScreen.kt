package com.example.thread_clone.screens.Splash

import android.widget.Space
import android.widget.Toast
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.thread_clone.R
import com.example.thread_clone.navigation.Routes
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.thread), contentDescription = "Thread",
            modifier = Modifier.size(1500.dp),
        )
    }

    LaunchedEffect(key1 = true) {

        delay(1000)

//        navController.navigate(Routes.Register.routes)
//        {
////                popUpTo(navController.graph.startDestinationId) // splash again not render direct close activity
////                launchSingleTop  = true
//            }
        if (FirebaseAuth.getInstance().currentUser != null) {
            navController.navigate(Routes.BottomNav.routes) {
                popUpTo(navController.graph.startDestinationId) // splash again not render direct close activity
                launchSingleTop = true
            }
        } else {

            navController.navigate(Routes.Register.routes) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }


//    Spacer(modifier = Modifier
//        .height(20.dp)
//        .background(Color.Blue))
//    Toast.makeText(LocalContext.current, "Hey task completed", Toast.LENGTH_SHORT).show()

}