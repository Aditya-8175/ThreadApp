package com.example.thread_clone.screens.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thread_clone.R
import com.example.thread_clone.navigation.Routes

@Preview
@Composable
fun LoginScreen(navController: NavHostController = rememberNavController()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize() ) {

        Image(painter = painterResource(id = R.drawable.heylogin), contentDescription = "Login" )

       Text(text = "Login ", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
        
//        Box(modifier = Modifier.height(20.dp))
        
        OutlinedTextField(value = email, onValueChange = { email = it} , label = {
            Text(text = "Email" )
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp) )
        
        OutlinedTextField(value = password, onValueChange = { password = it} , label = {
            Text(text = "password" )
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp))
        
        ElevatedButton(onClick = {

        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
        }

        TextButton(onClick = {
            navController.navigate(Routes.Register.routes){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop  = true

            }
        }) {
            Text(text = "New User ? Create A/C")
        }
    }
}