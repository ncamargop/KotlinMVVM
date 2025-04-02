package com.moviles.clothingapp.home.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.moviles.clothingapp.R
import com.moviles.clothingapp.home.data.Category
import com.moviles.clothingapp.ui.utils.dmSansFamily
import com.moviles.clothingapp.ui.utils.figtreeFamily


@Composable
fun CategorySection(categoryList: List<Category>, navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
        ) {
            Text(
                text = "Categorías",
                fontSize = 20.sp,
                fontFamily = dmSansFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 5.dp)
            )
        }

        // Lista de categorias
        LazyRow {
            items(categoryList) { category ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Card(
                        shape = CircleShape,
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier.size(80.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                    ) {
                        Image(
                            painter = painterResource(id = category.imageRes),
                            contentDescription = category.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                                .clickable{navController.navigate("discover/${category.searchName}")}
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = category.name, fontSize = 14.sp,
                        fontFamily = figtreeFamily, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}


val categoryList = listOf(
    Category("Camisetas", R.drawable.tshirt, "Camiseta"),
    Category("Chaquetas", R.drawable.jacket, "Chaqueta"),
    Category("Tenis", R.drawable.sneakers, "Tenis"),
    Category("Jeans", R.drawable.pants, "Jean"),
    Category("Sweaters", R.drawable.sweater, "Sweater"),


)
