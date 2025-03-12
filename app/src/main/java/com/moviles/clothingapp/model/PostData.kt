package com.moviles.clothingapp.model

import com.squareup.moshi.Json


/* Decorators help moshi assign values of the JSON: */
data class PostData(
    @Json(name="id") val id: String, //PK
    @Json(name="name") val name: String,
    @Json(name="price") val price: String,
    @Json(name="brand") val brand: String,
    @Json(name="category") val category: String,
    @Json(name="image") val image: String
)



