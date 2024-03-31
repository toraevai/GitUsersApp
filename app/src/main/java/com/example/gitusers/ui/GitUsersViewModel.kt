package com.example.gitusers.ui

import android.webkit.WebSettings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gitusers.api.GitService
import com.example.gitusers.api.USERS_PER_PAGE
import com.example.gitusers.data.GitRemoteMediator
import com.example.gitusers.db.GitUsersDatabase
import com.example.gitusers.model.User
import com.example.gitusers.model.fakeUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent
import javax.inject.Inject

@HiltViewModel
class GitUsersViewModel @Inject constructor(
    private val gitService: GitService,
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

    var user by mutableStateOf(fakeUser)

    fun getUser(userLogin: String, userAgent: String) {
        viewModelScope.launch {
            user = gitService.getUser(userLogin, userAgent)
        }
    }
}