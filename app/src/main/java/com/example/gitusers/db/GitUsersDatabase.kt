package com.example.gitusers.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gitusers.model.UserFromList

@Database(entities = [UserFromList::class], version = 1)
abstract class GitUsersDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}