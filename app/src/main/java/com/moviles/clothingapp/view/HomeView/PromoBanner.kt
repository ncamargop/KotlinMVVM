package com.moviles.clothingapp.view.HomeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviles.clothingapp.R
import com.moviles.clothingapp.ui.theme.dmSansFamily

@Composable
fun PromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(150.dp),
            contentAlignment = Alignment.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.promo_image),
            contentDescription = "Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))

        )
        Text(
            text = "Mejores precios",
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = dmSansFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp)
        )
    }

}
