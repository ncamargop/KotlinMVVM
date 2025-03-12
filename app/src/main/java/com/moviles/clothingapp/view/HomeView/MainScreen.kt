package com.moviles.clothingapp.view.HomeView


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.moviles.clothingapp.viewmodel.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MainScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SearchBar()
            QuickActions()
            PromoBanner()
            CategorySection(categoryList = categoryList)
            FeaturedProducts(viewModel)
        }
    }
}

