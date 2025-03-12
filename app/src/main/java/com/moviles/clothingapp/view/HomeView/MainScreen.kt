package com.moviles.clothingapp.view.HomeView


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.moviles.clothingapp.viewmodel.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moviles.clothingapp.viewmodel.WeatherViewModel


@Composable
fun MainScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(),
    weatherViewModel: WeatherViewModel = viewModel()
) {
    val banner = weatherViewModel.bannerType.observeAsState()
    Log.d("MainScreen", "Observed banner value: ${banner.value}")

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SearchBar()
            QuickActions()
            PromoBanner(bannerType = banner.value, navController = navController)
            CategorySection(categoryList = categoryList)
            FeaturedProducts(homeViewModel)
        }

    }
}
