package com.moviles.clothingapp.cart.data

import com.moviles.clothingapp.post.data.PostData

data class CartItemData(
    val product: PostData,
    val quantity: Int
)