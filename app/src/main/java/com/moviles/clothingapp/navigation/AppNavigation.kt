package com.moviles.clothingapp.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moviles.clothingapp.cart.CartViewModel
import com.moviles.clothingapp.cart.ui.CartScreen
import com.moviles.clothingapp.createPost.ui.CameraScreen
import com.moviles.clothingapp.createPost.ui.CreatePostScreen
import com.moviles.clothingapp.post.ui.DetailedPostScreen
import com.moviles.clothingapp.discover.ui.DiscoverScreen
import com.moviles.clothingapp.weatherBanner.ui.WeatherCategoryScreen
import com.moviles.clothingapp.home.ui.MainScreen
import com.moviles.clothingapp.login.ui.CreateAccountScreen
import com.moviles.clothingapp.login.ui.LoginScreen
import com.moviles.clothingapp.login.ui.ResetPasswordScreen
import com.moviles.clothingapp.map.ui.MapScreen
import com.moviles.clothingapp.home.HomeViewModel
import com.moviles.clothingapp.login.LoginViewModel
import com.moviles.clothingapp.post.PostViewModel
import com.moviles.clothingapp.login.ResetPasswordViewModel
import com.moviles.clothingapp.weatherBanner.WeatherViewModel


/* Navigation component called to change between pages
*   - each page is a composable, here we define the routes to call, and which component it invokes.
*   - all the other pages must be added below the home screen composable.
*   - Each composable here declares a route which must be the same stated in the component.
* */
@Composable
fun AppNavigation(navController: NavHostController,
                  loginViewModel: LoginViewModel,
                  resetPasswordViewModel: ResetPasswordViewModel,
                  weatherViewModel: WeatherViewModel,
                  cartViewModel: CartViewModel
) {

    /* Check if user is already logged in and navigate accordingly */
    val isUserLoggedIn by loginViewModel.navigateToHome.collectAsState()

    /* Start navigation in login page. Route: login */
    NavHost(navController = navController, startDestination = if (isUserLoggedIn) "home" else "login") {
        composable("login") {
            LoginScreen(
                loginViewModel = loginViewModel,
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                navController = navController,
                weatherViewModel = weatherViewModel
            )
        }

        /* Create account page. Route: createAccount */
        composable("createAccount") {
            CreateAccountScreen(loginViewModel, navController)
        }

        /* Reset password page. Route: resetPassword */
        composable("resetPassword") {
            ResetPasswordScreen(resetPasswordViewModel = resetPasswordViewModel, navController)
        }

        /* Home/Main page. Route: home */
        composable("home") {
            val homeViewModel: HomeViewModel = viewModel()
            MainScreen(navController, homeViewModel, weatherViewModel)

        }

        /* Add more pages here below: */

        /* Category page for the promo banner based on weather. Route: category/  */
        composable(
            route = "category/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: "sale"
            val postViewModel: PostViewModel = viewModel()
            WeatherCategoryScreen(categoryId = categoryId, navController, viewModel = postViewModel)
        }


        /* Discover page to show all posts. Route: discover/   */
        composable("discover/{query}") { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            val postViewModel: PostViewModel = viewModel()
            DiscoverScreen(navController, postViewModel, query)
        }

        composable(
            route = "detailedPost/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: 0
            val postViewModel: PostViewModel = viewModel()
            DetailedPostScreen(
                productId = postId,
                viewModel = postViewModel,
                cartViewModel,
                onBack = { navController.popBackStack() },
                onNavigateToCart = { navController.navigate("cart")}
            )
        }

        composable("cart") {
            CartScreen(navController = navController, cartViewModel)
        }


        composable("camera") {
            CameraScreen(navController)
        }

        composable("createPost/{encodedUri}") { backStackEntry ->
            val encodedUri = backStackEntry.arguments?.getString("encodedUri") ?: ""
            val decodedUri = Uri.decode(encodedUri)
            CreatePostScreen(navController, decodedUri)
        }


        composable("map/") {
            MapScreen(navController)
        }



    }


}
