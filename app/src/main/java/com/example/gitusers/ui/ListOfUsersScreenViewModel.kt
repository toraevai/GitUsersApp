package com.example.gitusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gitusers.api.GitService
import com.example.gitusers.api.USERS_PER_PAGE
import com.example.gitusers.data.GitPagingSource
import com.example.gitusers.data.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListOfUsersScreenViewModel @Inject constructor(private val usersRepository: UsersRepository, private val gitService: GitService) :
    ViewModel() {
    val flow = Pager(
        PagingConfig(
            pageSize = USERS_PER_PAGE,
            enablePlaceholders = false
        )
    ) {
        GitPagingSource(gitService)
    }.flow
        .cachedIn(viewModelScope)
}