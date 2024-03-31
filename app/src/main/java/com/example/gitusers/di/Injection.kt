package com.example.gitusers.di

import android.content.Context
import androidx.room.Room
import com.example.gitusers.api.GitService
import com.example.gitusers.db.GitUsersDatabase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object Injection {
    private const val baseUrl = "https://api.github.com/"
    private val json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideApi(): Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    @Provides
    @Singleton
    fun provideGitService(retrofit: Retrofit): GitService = retrofit.create(GitService::class.java)
//
//    @Provides
//    @Singleton
//    fun provideUsersRepository(gitService: GitService): UsersRepository =
//        UsersRepository(gitService)

    @Provides
    @Singleton
    fun provideGitUsersDatabase(@ApplicationContext context: Context): GitUsersDatabase =
        Room.databaseBuilder(context, GitUsersDatabase::class.java, "users").build()
}