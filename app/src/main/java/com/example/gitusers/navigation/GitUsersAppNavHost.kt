package com.example.gitusers.navigation

import android.content.Context
import android.webkit.WebSettings
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gitusers.ui.GitUsersViewModel
import com.example.gitusers.ui.ListOfUserScreen
import com.example.gitusers.ui.ListOfUsersDestination
import com.example.gitusers.ui.UserDetailsDestination
import com.example.gitusers.ui.UserDetailsScreen

@Composable
fun GitUsersAppNavHost() {
    val gitUsersViewModel = hiltViewModel<GitUsersViewModel>()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ListOfUsersDestination.route
    ) {
        composable(route = ListOfUsersDestination.route) {
            ListOfUserScreen(
                gitUsersViewModel = gitUsersViewModel,
                navigateToUserDetails = { userLogin ->
                    navController.navigate(UserDetailsDestination.route + "/{$userLogin}")
                }
            )
        }
        composable(
            route = UserDetailsDestination.route + "/{userLogin}",
        ) { backStackEntry ->
            gitUsersViewModel.getUser(backStackEntry.arguments?.getString("userLogin")!!)
            val user = gitUsersViewModel.user
            UserDetailsScreen(user)
        }
    }
}