package org.certificatic.phoneagendacomposeexample.composables

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.certificatic.phoneagendacomposeexample.viewmodel.UserListViewModel

@Composable
fun UserList(
    navigationController: NavHostController,
    viewModel: UserListViewModel
) {
    val users by viewModel.userList.collectAsState()

    Scaffold() {

        if (users.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Loader()
            }

        } else {
            LazyColumn() {
                items(users.size) { index ->

                    val user = users[index]

                    UserItem(
                        user = user
                    ) {

                        navigationController.navigate("userDetail/${user.id}")

                    }
                    Divider()
                }
            }
        }
    }
}