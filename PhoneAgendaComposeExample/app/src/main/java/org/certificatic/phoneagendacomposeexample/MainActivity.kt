package org.certificatic.phoneagendacomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import org.certificatic.phoneagendacomposeexample.composables.UserDetail
import org.certificatic.phoneagendacomposeexample.composables.UserList
import org.certificatic.phoneagendacomposeexample.dto.UsuarioDTO
import org.certificatic.phoneagendacomposeexample.ui.theme.PhoneAgendaComposeExampleTheme
import org.certificatic.phoneagendacomposeexample.viewmodel.UserListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<UserListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PhoneAgendaComposeExampleTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "usersList"
                ) {

                    composable("usersList") {
                        val userLisrViewModel by viewModels<UserListViewModel>()
                        UserList(
                            navController,
                            userLisrViewModel
                        )
                    }
                    composable("userDetail/{userId}") {
                        /*val userId =
                            navController.currentBackStackEntry?.arguments?.get("userId")*/
                        val userId = navArgument("userId") { type = NavType.StringType }.name

                        viewModel.userList.collectAsState().value.find { user -> user.id == userId }
                            ?.let {
                                //Composable
                                UserDetail(it)
                            }
                    }
                }
            }
        }

        this.viewModel.loadUsers()
    }
}