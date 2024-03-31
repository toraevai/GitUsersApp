package com.example.gitusers.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.gitusers.api.GitService
import com.example.gitusers.db.GitUsersDatabase
import com.example.gitusers.model.UserFromList
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class GitRemoteMediator(
    private val gitUsersDatabase: GitUsersDatabase,
    private val gitService: GitService
) : RemoteMediator<Int, UserFromList>() {
    val userDao = gitUsersDatabase.userDao()
    override suspend fun load(loadType: LoadType, state: PagingState<Int, UserFromList>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id ?: 0
                }
                LoadType.PREPEND -> {
                    val firsItem = state.firstItemOrNull()
                    if (firsItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    firsItem.id
                }

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    lastItem.id
                }
            }
            val response = gitService.getUsers(loadKey)

            gitUsersDatabase.withTransaction {
                userDao.insertAll(response)
            }

            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
}