package com.example.gitusers.api

import com.example.gitusers.model.User
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val USERS_PER_PAGE = 30

interface GitService {

    @Headers("accept: application/vnd.github+json")
    @GET("users?per_page=${USERS_PER_PAGE}")
    suspend fun getUsers(
        @Query("since") userId: Int
    ): List<User>
}