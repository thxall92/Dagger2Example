package com.eunhye.dagger2example.network

import com.eunhye.dagger2example.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */

interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}