package com.example.gitusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gitusers.api.GitService
import com.example.gitusers.api.USERS_PER_PAGE
import com.example.gitusers.data.GitPagingSource
import com.example.gitusers.data.GitRemoteMediator
import com.example.gitusers.data.UsersRepository
import com.example.gitusers.db.GitUsersDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListOfUsersScreenViewModel @Inject constructor(
    gitService: GitService,
    gitUsersDatabase: GitUsersDatabase
) : ViewModel() {
    val userDao = gitUsersDatabase.userDao()
    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        PagingConfig(
            pageSize = USERS_PER_PAGE,
            enablePlaceholders = false
        ),
        remoteMediator = GitRemoteMediator(
            gitUsersDatabase = gitUsersDatabase,
            gitService = gitService
        )
    ) {
        userDao.pagingSource()
    }.flow
        .cachedIn(viewModelScope)
}