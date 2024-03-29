package com.example.gitusers.api

import com.example.gitusers.model.User

data class UsersResponse(
    val users: List<User>,
    val total: Int,
    val page: Int
)