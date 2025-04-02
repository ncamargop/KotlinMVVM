package com.moviles.clothingapp.home.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.moviles.clothingapp.home.HomeViewModel
import com.moviles.clothingapp.post.ui.PostItem
import com.moviles.clothingapp.ui.utils.dmSansFamily
import com.moviles.clothingapp.ui.utils.figtreeFamily


/* SECCION DESTACADOS */
@Composable
fun FeaturedProducts(navController: NavController, viewModel: HomeViewModel) {
    val allProducts by viewModel.postData.observeAsState(emptyList())
    val products = allProducts.takeLast(6)

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 8.dp)
        ) {
            Text(
                text = "Recién llegados",
                fontSize = 20.sp,
                fontFamily = dmSansFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 5.dp)
            )
        }

        // Wrap in a Box with height to avoid infinite scrolling error
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp) // Set finite height
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(products) { product ->
                    PostItem(product) {
                        navController.navigate("detailedPost/${product.id}")
                    }
                }
            }
        }
    }
}
