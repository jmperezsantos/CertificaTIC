package org.certificatic.phoneagendacomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dagger.hilt.android.AndroidEntryPoint
import org.certificatic.phoneagendacomposeexample.composables.UserDetail
import org.certificatic.phoneagendacomposeexample.composables.UserItem
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
                        UserList(
                            navController,
                            viewModel.userList
                        )
                    }
                    composable("userDetail/{userId}") {
                        val userId =
                            navController.currentBackStackEntry?.arguments?.get("userId")

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