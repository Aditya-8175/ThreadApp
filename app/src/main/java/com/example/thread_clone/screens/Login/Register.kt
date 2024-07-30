package com.example.thread_clone.screens.Login

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.thread_clone.R
import com.example.thread_clone.navigation.Routes
import com.example.thread_clone.viewmodel.AuthViewModel

@Composable
fun Register(navController: NavHostController) {

//     viewModal initialization
    val authViewModel : AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState(null)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }

    var imageuri by remember { mutableStateOf<Uri?>(null) }

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        android.Manifest.permission.READ_MEDIA_IMAGES
    } else android.Manifest.permission.READ_EXTERNAL_STORAGE

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri: Uri? ->
        imageuri = uri
    }

    val permissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        isGranted : Boolean ->
        if (isGranted) {

        } else {

        }

    }

    LaunchedEffect(firebaseUser != null) {
        navController.navigate(Routes.BottomNav.routes){
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop  = true

        }

    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(text = "Register here", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold )

        Box(modifier = Modifier.height(25.dp))

        Image(painter = if(imageuri == null) painterResource(id = R.drawable.people)
            else rememberAsyncImagePainter(model = imageuri) , modifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable {

                val isGranted = ContextCompat.checkSelfPermission(
                    context,
                    permissionToRequest
                ) == PackageManager.PERMISSION_GRANTED

                if (isGranted) {
                    launcher.launch("image/*")
                } else {
                    permissionsLauncher.launch(permissionToRequest)
                }

            }, contentScale = ContentScale.Crop,
             contentDescription = "People")

        Box(modifier = Modifier.height(25.dp))


        OutlinedTextField(value = name, onValueChange = { name = it }, label = {
            Text(text = "Name")
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), singleLine = true)

        OutlinedTextField(value = username, onValueChange = { username = it }, label = {
            Text(text = "Username")
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), singleLine = true)

        OutlinedTextField(value = bio, onValueChange = { bio = it }, label = {
            Text(text = "Bio")
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = {
            Text(text = "Email")
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), singleLine = true)

        OutlinedTextField(value = password, onValueChange = { password = it }, label = {
            Text(text = "Password")
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), singleLine = true)


        ElevatedButton(onClick = {
            if( name.isEmpty() || username.isEmpty() || bio.isEmpty() || imageuri == null || password.isEmpty()) {
                Toast.makeText(context, "Please fill all details", Toast.LENGTH_SHORT).show()
            }else{
                authViewModel.register(email, password, name, bio, username, imageuri!!, context)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Create Account", fontWeight = FontWeight.Bold)
        }

        TextButton(onClick = {
            navController.navigate(Routes.Login.routes){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop  = true

            }
        }) {
            Text(text = "Already have A/C ? Login to A/C")
        }

    }
}