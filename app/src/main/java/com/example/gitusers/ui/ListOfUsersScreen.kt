package com.example.gitusers.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gitusers.R
import com.example.gitusers.model.UserFromList
import com.example.gitusers.navigation.NavigationDestination

object ListOfUsersDestination : NavigationDestination {
    override val route = "list_of_users"
}

@Composable
fun ListOfUserScreen(gitUsersViewModel: GitUsersViewModel, navigateToUserDetails: (String) -> Unit) {
    val users = gitUsersViewModel.flow.collectAsLazyPagingItems()
    LazyColumn() {
        items(count = users.itemCount) { index ->
            UserInList(
                userFromList = users[index]!!,
                onClick = { navigateToUserDetails(users[index]!!.login) }
            )
        }
    }
}

@Composable
fun UserInList(
    userFromList: UserFromList,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        UserAvatar(url = userFromList.avatarUrl)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "User id: ${userFromList.id}, login: ${userFromList.login}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun UserAvatar(url: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(url)
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.user_avatar),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(120.dp)
        )
    }
}