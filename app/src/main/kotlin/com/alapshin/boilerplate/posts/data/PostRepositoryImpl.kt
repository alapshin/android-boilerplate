package com.alapshin.boilerplate.posts.data

import com.alapshin.boilerplate.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val apiService: ApiService) : PostRepository {
    override fun getPosts(): Observable<List<Post>> {
        return apiService.getPosts().toObservable()
    }
}
