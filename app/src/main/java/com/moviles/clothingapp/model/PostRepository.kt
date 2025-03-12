package com.moviles.clothingapp.repository

import android.util.Log
import com.moviles.clothingapp.model.PostData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class PostRepository {

    private val BASE_URL = "http://10.0.2.2:8000/" // this URL of localhost since we run in emulator

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    // Fetch all products
    suspend fun fetchRepository(): List<PostData>? {
        return try {
            val response = apiService.fetchClothes()
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("PostRepository", "Response failed: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("PostRepository", "Error: ${e.message}")
            null
        }
    }


    // Fetch products by category
    suspend fun fetchProductsByCategory(categoryId: String): List<PostData>? {
        return try {
            val response = apiService.fetchClothesByCategory(categoryId)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            Log.e("PostRepository", "Error fetching category: ${e.message}")
            null
        }
    }



    /* Retrieves information using FAST API, testing with this return format:
    *       Retrofit retrieves the information which has this return format:
    *       [{name: "", price:"", brand:"", image:""}, {...}, ...]
    *       JSON response is parsed to our model class with moshi.
    *
    * */
    interface ApiService {
        @GET("clothing")
        suspend fun fetchClothes(): Response<List<PostData>>


        @GET("clothing/category/{categoryId}") // Fetch by category
        suspend fun fetchClothesByCategory(@retrofit2.http.Path("categoryId") categoryId: String): Response<List<PostData>>
    }


}
