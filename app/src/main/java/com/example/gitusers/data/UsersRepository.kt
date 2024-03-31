//package com.example.gitusers.data
//
//import com.example.gitusers.api.GitService
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class UsersRepository @Inject constructor(private val gitService: GitService) {
//    suspend fun getUsers(userId: Int) = gitService.getUsers(userId = userId)
//}