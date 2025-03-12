package com.moviles.clothingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.moviles.clothingapp.navigation.AppNavigation
import com.moviles.clothingapp.viewmodel.LoginViewModel
import com.moviles.clothingapp.viewmodel.ResetPasswordViewModel

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var resetPasswordViewModel: ResetPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        loginViewModel = LoginViewModel(auth)
        resetPasswordViewModel = ResetPasswordViewModel(auth)

        setContent {
            val navController = rememberNavController()
            AppNavigation(navController, loginViewModel, resetPasswordViewModel) //Pass the viewmodel.
        }
    }
}