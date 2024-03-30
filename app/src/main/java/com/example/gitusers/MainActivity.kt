package com.example.gitusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.gitusers.ui.ListOfUserScreen
import com.example.gitusers.ui.ListOfUsersScreenViewModel
import com.example.gitusers.ui.theme.GitUsersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val listOfUsersScreenViewModel = hiltViewModel<ListOfUsersScreenViewModel>()
            GitUsersTheme {
                ListOfUserScreen(listOfUsersScreenViewModel = listOfUsersScreenViewModel)
            }
        }
    }
}
