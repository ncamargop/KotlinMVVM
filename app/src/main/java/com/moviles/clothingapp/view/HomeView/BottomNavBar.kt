package com.moviles.clothingapp.view.HomeView

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChatBubbleOutline
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.PersonOutline
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.moviles.clothingapp.ui.theme.DarkGreen


@Composable

// TODO: Implementar la funcion de ir a donde se pulsa para cada boton.
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = DarkGreen
    ) {
        val items = listOf(
            BottomNavItem("Home", Icons.Rounded.Home),
            BottomNavItem("Explore", Icons.Rounded.Explore),
            BottomNavItem("Cart", Icons.Rounded.ShoppingCart),
            BottomNavItem("Chat", Icons.Rounded.ChatBubbleOutline),
            BottomNavItem("Profile", Icons.Rounded.PersonOutline)
        )

        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label, modifier = Modifier.size(32.dp),
                    tint = Color.White) },
                selected = currentRoute == item.label,
                onClick = {
                    navController.navigate(item.label) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val label: String, val icon: ImageVector)
