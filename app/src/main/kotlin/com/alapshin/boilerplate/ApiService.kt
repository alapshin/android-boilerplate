package com.alapshin.boilerplate

import com.alapshin.boilerplate.posts.data.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Single<Post>
    @GET("posts/")
    fun getPosts(@Query("_page") page: Int): Single<List<Post>>
}
