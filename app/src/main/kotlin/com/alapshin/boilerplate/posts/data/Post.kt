package com.alapshin.boilerplate.posts.data

import com.squareup.moshi.Json

data class Post(
    @Json(name = "id") val id: Int,
    @Json(name = "userId") val userId: Int,
    @Json(name = "body") val body: String,
    @Json(name = "title") val title: String
)
