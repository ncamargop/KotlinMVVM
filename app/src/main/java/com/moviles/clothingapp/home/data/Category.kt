package com.moviles.clothingapp.home.data


import androidx.annotation.DrawableRes

data class Category(
    val name: String,
    @DrawableRes val imageRes: Int,
    val searchName: String)
