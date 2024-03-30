package com.example.gitusers.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "users")
@Serializable
data class User(
    @PrimaryKey val id: Int,
    @SerialName("avatar_url") val avatarUrl: String,
    val login: String
)