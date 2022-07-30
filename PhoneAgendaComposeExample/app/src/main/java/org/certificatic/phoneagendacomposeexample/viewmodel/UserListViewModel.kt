package org.certificatic.phoneagendacomposeexample.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.certificatic.phoneagendacomposeexample.dto.UsuarioDTO
import org.certificatic.phoneagendacomposeexample.services.UserServiceWS
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private var userService: UserServiceWS
) : ViewModel() {

    //Propiedad que almacena la información
    private val users: MutableLiveData<List<UsuarioDTO>> = MutableLiveData<List<UsuarioDTO>>()

    private val _userList = MutableStateFlow(listOf<UsuarioDTO>())
    val userList: StateFlow<List<UsuarioDTO>> get() = _userList

    fun getUsers(): LiveData<List<UsuarioDTO>> {
        return users
    }

    //Consumo de WS o BD
    fun loadUsers() {
        this.userService.getAllUsers({ users ->
            this.users.value = users

            viewModelScope.launch{
                withContext(Dispatchers.Default){
                    _userList.emit(users)
                }
            }

        }, { error ->
            Log.e("MPS", error)
        })
    }

}