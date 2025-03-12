package com.moviles.clothingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moviles.clothingapp.view.HomeView.MainScreen
import com.moviles.clothingapp.view.Login.CreateAccountScreen
import com.moviles.clothingapp.view.Login.LoginScreen
import com.moviles.clothingapp.view.Login.ResetPasswordScreen
import com.moviles.clothingapp.viewmodel.LoginViewModel
import com.moviles.clothingapp.viewmodel.ResetPasswordViewModel



/* Navigation component called to change between pages
*   - each page is a composable, here we define the routes to call, and which component it invokes.
*   - all the other pages must be added below the home screen composable.
* */
@Composable
fun AppNavigation(navController: NavHostController, loginViewModel: LoginViewModel, resetPasswordViewModel: ResetPasswordViewModel) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                loginViewModel = loginViewModel,
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                navController = navController
            )
        }

        composable("createAccount") {
            CreateAccountScreen(loginViewModel, navController)
        }
        composable("resetPassword") {
            ResetPasswordScreen(resetPasswordViewModel = resetPasswordViewModel, navController)
        }

        composable("home") {
            MainScreen(navController)
        }

        // Add more pages below:
    }
}
