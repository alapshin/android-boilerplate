package com.alapshin.boilerplate.posts.data

import com.alapshin.boilerplate.ApiService
import io.reactivex.Single
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val apiService: ApiService) : PostRepository {
    override fun getPost(id: Int): Single<Post> {
        return apiService.getPost(id)
    }

    override fun getPosts(page: Int): Single<List<Post>> {
        return apiService.getPosts(page)
    }
}
