package org.certificatic.phoneagendacomposeexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.certificatic.phoneagendacomposeexample.dto.UsuarioDTO
import org.certificatic.phoneagendacomposeexample.services.UserService
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    //Informa cuando el usuario es creado
    private val userCreated: MutableLiveData<String> =
        MutableLiveData<String>()

    //Informa cuando ocurre un error
    private val errorOccurred: MutableLiveData<String> =
        MutableLiveData<String>()

    //Observables
    fun isUserCreated(): LiveData<String> {
        return userCreated
    }

    fun didErrorOccur(): LiveData<String> {
        return errorOccurred
    }

    fun createUser(usuarioDTO: UsuarioDTO) {

        //TODO Lógica para guardar en BD o WS

        this.userService.createUser(usuarioDTO,
            { idUser ->
                Log.i("MPS", "Id: $idUser")
                this.userCreated.value = idUser
            }, { error ->
                Log.e("MPS", error)
                this.errorOccurred.value = error
            })
    }

}