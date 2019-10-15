package com.alapshin.boilerplate.posts.data

import io.reactivex.Single

interface PostRepository {
    fun getPost(id: Int): Single<Post>
    fun getPosts(page: Int): Single<List<Post>>
}
