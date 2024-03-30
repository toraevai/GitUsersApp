package com.example.gitusers.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitusers.api.GitService
import com.example.gitusers.api.USERS_PER_PAGE
import com.example.gitusers.model.User
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitPagingSource @Inject constructor(
    private val gitService: GitService
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val position = params.key ?: 0
            val users = gitService.getUsers(position)
            val nextUserId = if (users.isEmpty()) {
                null
            } else {
                position + (params.loadSize / USERS_PER_PAGE)
            }
            return LoadResult.Page(
                data = users,
                prevKey = if (position.toInt() == 0) null else position - (params.loadSize / USERS_PER_PAGE),
                nextKey = nextUserId
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}