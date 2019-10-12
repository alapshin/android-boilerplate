package com.alapshin.boilerplate

import com.alapshin.boilerplate.posts.data.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts?_start=0&_limit=10")
    fun getPosts(): Single<List<Post>>
    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Single<Post>
}
