package com.example.gitusers.api

import com.example.gitusers.model.User
import com.example.gitusers.model.UserFromList
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val USERS_PER_PAGE = 30

interface GitService {

    @Headers("accept: application/vnd.github+json")
    @GET("users?per_page=${USERS_PER_PAGE}")
    suspend fun getUsers(
        @Query("since") userId: Int
    ): List<UserFromList>

    @Headers("accept: application/vnd.github+json")
    @GET("users/{userLogin}")
    suspend fun getUser(
        @Path("userLogin") userLogin: String,
        @Header("User-Agent") userAgent: String
    ): User
}