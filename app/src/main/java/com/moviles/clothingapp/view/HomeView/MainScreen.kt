package com.moviles.clothingapp.view.HomeView


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.moviles.clothingapp.viewmodel.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.Trace
import com.moviles.clothingapp.R
import com.moviles.clothingapp.viewmodel.WeatherViewModel


@Composable
fun MainScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(),
    weatherViewModel: WeatherViewModel = viewModel()
) {
    val banner = weatherViewModel.bannerType.observeAsState()
    val searchText = remember { mutableStateOf("") } // Store search text
    Log.d("MainScreen", "Observed banner value: ${banner.value}")

    val trace: Trace = remember { FirebasePerformance.getInstance().newTrace("MainScreen_Loading") }
    LaunchedEffect(Unit) {
        trace.start() // Start tracing when screen loads
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(vertical = 1.dp),
            verticalArrangement = Arrangement.Center,  // Center vertically
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(100.dp)
            )

            SearchBar(
                searchText = searchText.value,
                onSearchTextChange = { newText -> searchText.value = newText },
                onSearchSubmit = {
                    navController.navigate("discover/${searchText.value}")
                })
            QuickActions()
            PromoBanner(bannerType = banner.value, navController = navController)
            CategorySection(categoryList = categoryList)
            FeaturedProducts(homeViewModel)

        }

    }

    // Stop trace metric (ms) when banner has been loaded
    LaunchedEffect(banner.value) {
        trace.stop()
    }
}
