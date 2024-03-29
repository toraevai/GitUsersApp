package com.example.gitusers.db

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gitusers.model.User

interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM users WHERE id LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, User>

    @Query("DELETE FROM users")
    suspend fun clearAll()
}