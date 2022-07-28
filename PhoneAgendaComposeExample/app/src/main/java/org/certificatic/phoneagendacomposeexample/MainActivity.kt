package org.certificatic.phoneagendacomposeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import org.certificatic.phoneagendacomposeexample.dto.UsuarioDTO
import org.certificatic.phoneagendacomposeexample.services.UserServiceWS
import org.certificatic.phoneagendacomposeexample.ui.theme.PhoneAgendaComposeExampleTheme
import org.certificatic.phoneagendacomposeexample.viewmodel.UserListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var viewModel: UserListViewModel

    private var users: List<UsuarioDTO> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        this.viewModel.getUsers().observe(this) {

            this.users = it

        }

        setContent {
            PhoneAgendaComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //UserList("Android")
                }
            }
        }
    }
}

@Composable
fun UserList(users:List<UsuarioDTO>) {

    LazyColumn{
        //users.
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhoneAgendaComposeExampleTheme {
        UserList("Android")
    }
}