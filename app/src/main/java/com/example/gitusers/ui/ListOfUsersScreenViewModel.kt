package com.example.gitusers.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitusers.data.UsersRepository
import com.example.gitusers.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfUsersScreenViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {
    var users: List<User> by mutableStateOf(listOf<User>())

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            users = usersRepository.getUsers()
        }
    }
}