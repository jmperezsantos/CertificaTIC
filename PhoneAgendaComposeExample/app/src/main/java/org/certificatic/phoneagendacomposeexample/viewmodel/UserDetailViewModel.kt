package org.certificatic.phoneagendacomposeexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.certificatic.phoneagendacomposeexample.dto.UsuarioDTO
import org.certificatic.phoneagendacomposeexample.services.UserServiceWS
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userService: UserServiceWS
) : ViewModel() {

    private val userUpdated: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>()
    private val userDeleted: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>()

    fun isUserUpdated(): LiveData<Boolean> {
        return userUpdated
    }

    fun isUserDeleted(): LiveData<Boolean> {
        return userDeleted
    }

    fun updateUser(usuario: UsuarioDTO) {

        this.userService.updateUser(usuario,
            { userUpdated ->
                this.userUpdated.value = true
            },
            { error ->
                Log.e("MPS", error)
                this.userUpdated.value = false
            })

    }

    fun deleteUser(usuario: UsuarioDTO) {

        this.userService.deleteUser(usuario.id!!, {

            this.userDeleted.value = true

        }, { error ->
            Log.e("MPS", error)
            this.userDeleted.value = false
        })

    }

}