package com.alapshin.boilerplate.posts.data

import io.reactivex.Observable

interface PostRepository {
    fun getPosts(): Observable<List<Post>>
    fun getPost(id: Int): Observable<Post>
}
