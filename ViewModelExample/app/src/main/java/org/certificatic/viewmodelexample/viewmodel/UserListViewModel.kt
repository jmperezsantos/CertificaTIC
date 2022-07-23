package org.certificatic.viewmodelexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.certificatic.viewmodelexample.dto.UsuarioDTO
import org.certificatic.viewmodelexample.services.UserServiceWS
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private var userService: UserServiceWS
    ) : ViewModel() {

    //Propiedad que almacena la información
    private val users: MutableLiveData<List<UsuarioDTO>> = MutableLiveData<List<UsuarioDTO>>()

    fun getUsers(): LiveData<List<UsuarioDTO>> {
        return users
    }

    //Consumo de WS o BD
    fun loadUsers() {
        this.userService.getAllUsers({ users ->
            this.users.value = users
        }, { error ->
            Log.e("MPS", error)
        })
    }

}