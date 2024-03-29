package com.example.gitusers.api

import com.example.gitusers.model.User
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GitService {

    @Headers("accept: application/vnd.github+json")
    @GET("users?per_page=100")
    suspend fun getUsers(): List<User>
}