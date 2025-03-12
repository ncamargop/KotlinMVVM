package com.moviles.clothingapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviles.clothingapp.model.PostData
import com.moviles.clothingapp.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()
    private val _posts = MutableStateFlow(emptyList<PostData>())
    val posts: StateFlow<List<PostData>> get() = _posts


    // Fetch products by category
    fun fetchPostsByCategory(categoryId: String) {
        viewModelScope.launch {
            try {
                val result = repository.fetchProductsByCategory(categoryId)
                _posts.value = result ?: emptyList()
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error fetching category $categoryId: ${e.message}")
                _posts.value = emptyList()
            }
        }
    }
}

