package com.example.gitusers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    @SerialName("avatar_url") val avatarUrl: String,
    val login: String
)