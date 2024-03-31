package com.example.gitusers.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gitusers.model.User
import com.example.gitusers.model.fakeUser
import com.example.gitusers.navigation.NavigationDestination

object UserDetailsDestination : NavigationDestination {
    override val route = "user_details"
}

@Composable
fun UserDetailsScreen(user: User) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        UserAvatar(url = user.avatarUrl)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Name: ${user.name}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Email: ${user.email ?: "No email"}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Org: ${user.orgUrl}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Following: ${user.following}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Followers: ${user.followers}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun UserDetailsScreenPreview() {
    MaterialTheme {
        UserDetailsScreen(fakeUser)
    }
}