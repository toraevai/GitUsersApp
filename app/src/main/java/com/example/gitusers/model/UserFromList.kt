package com.example.gitusers.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "users")
@Serializable
data class UserFromList(
    @PrimaryKey val id: Int,
    @SerialName("avatar_url") val avatarUrl: String,
    val login: String
)

@Serializable
data class User(
    @SerialName("avatar_url") val avatarUrl: String,
    val name: String,
    val email: String?,
    @SerialName("organizations_url") val orgUrl: String,
    val following: Int,
    val followers: Int,
    @SerialName("created_at") val created: String
)

val fakeUser: User = User(
    avatarUrl = "",
    name = "demo",
    email = "demo@example.com",
    orgUrl = "",
    following = 0,
    followers = 0,
    created = "01.01.2000"
)