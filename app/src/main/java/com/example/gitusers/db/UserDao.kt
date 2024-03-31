package com.example.gitusers.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gitusers.model.UserFromList

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userFromLists: List<UserFromList>)

    @Query("SELECT * FROM users")
    fun pagingSource(): PagingSource<Int, UserFromList>

    @Query("DELETE FROM users")
    suspend fun clearAll()
}