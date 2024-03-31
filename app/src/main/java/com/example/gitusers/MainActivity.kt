package com.example.gitusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gitusers.navigation.GitUsersAppNavHost
import com.example.gitusers.ui.ListOfUserScreen
import com.example.gitusers.ui.GitUsersViewModel
import com.example.gitusers.ui.theme.GitUsersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitUsersTheme {
                GitUsersAppNavHost()
            }
        }
    }
}
