package com.moviles.clothingapp.cart

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.moviles.clothingapp.cart.data.CartItemData
import com.moviles.clothingapp.post.data.PostData

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItemData>()
    val cartItems: List<CartItemData> get() = _cartItems

    fun addToCart(product: PostData) {
        Log.d("CART", "Adding product to cart: ${product.name}, ID: ${product.id}, Price: ${product.price}")
        _cartItems.add(CartItemData(product, 1))
    }

    fun removeFromCart(productId: String) {
        _cartItems.removeIf { it.product.id == productId }
    }

}

