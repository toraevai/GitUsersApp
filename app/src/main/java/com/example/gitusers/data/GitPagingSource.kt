package com.example.gitusers.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitusers.model.User

class GitPagingSource(
    private val backend: ExampleBackendService,
    private val query: String
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = backend.searchUsers(query, nextPageNumber)
            return LoadResult.Page(
                data = response.users,
                prevKey = null, // Only paging forward.
                nextKey = response.nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}