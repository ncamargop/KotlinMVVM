package com.moviles.clothingapp.view.Discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.moviles.clothingapp.model.PostData
import com.moviles.clothingapp.ui.theme.figtreeFamily
import com.moviles.clothingapp.viewmodel.PostViewModel


/* Screen to see after clicking the home's promoBanner:
*   - Features all products for a given category based on the user's weather.
*   - The retrieved data needs to have a 'category' variable.
*   - TODO: link each post to its detailed page.
*/
@Composable
fun WeatherCategoryScreen(categoryId: String, navController: NavController, viewModel: PostViewModel) {
    // Fetch data on first composition
    LaunchedEffect(categoryId) {
        viewModel.fetchPostsByCategory(categoryId)
    }

    val posts by viewModel.posts.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = "Back")
            }
            Text(
                text = getCategoryTitle(categoryId),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Display loading or posts
        if (posts.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(8.dp)
            ) {
                items(posts) { post ->
                    PostItem(post)
                }
            }
        }
    }
}

@Composable
fun PostItem(post: PostData) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = post.image,
                contentDescription = post.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.brand, fontFamily = figtreeFamily ,fontWeight = FontWeight.Normal, fontSize = 14.sp)
            Text(text = post.name, fontFamily = figtreeFamily ,fontWeight = FontWeight.Medium, fontSize = 18.sp)
            Text(text = "$${post.price}", fontFamily = figtreeFamily, fontSize = 16.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

// Helper function to get readable category titles
fun getCategoryTitle(categoryId: String): String {
    return when (categoryId) {
        "summer" -> "Ropa Ligera para el Calor"
        "winter" -> "Ropa Abrigada"
        "rain" -> "Especial para Días Lluviosos"
        "cloudy" -> "Las Mejores Prendas para Días nublados "
        "sale" -> "Mejores Precios"
        else -> "Productos"
    }
}
