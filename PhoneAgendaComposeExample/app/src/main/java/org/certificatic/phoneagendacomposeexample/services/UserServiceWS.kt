package org.certificatic.phoneagendacomposeexample.services

import android.util.Log
import org.certificatic.phoneagendacomposeexample.dto.UsuarioDTO
import org.certificatic.phoneagendacomposeexample.wsclient.UserWSClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserServiceWS (
    private var userWSClient: UserWSClient
) {

    fun getAllUsers(
        success: (List<UsuarioDTO>) -> Unit,
        error: (String) -> Unit
    ) {

        //Esta línea de código ejecuta la llamada en el hilo principal (Incorrecto)
        //val response = this.userWSClient.getAllUsers().execute()

        //Esta línea de código ejecuta la llamada en un hilo secundario (CORRECTO)
        this.userWSClient.getAllUsers()
            .enqueue(object : Callback<Map<String, UsuarioDTO>> {
                override fun onResponse(
                    call: Call<Map<String, UsuarioDTO>>,
                    response: Response<Map<String, UsuarioDTO>>
                ) {
                    if (response.isSuccessful) {

                        val usuarios = mutableListOf<UsuarioDTO>()

                        val usersMap = response.body()!!
                        usersMap.keys.forEach { clave ->
                            val user = usersMap[clave]!!
                            user.id = clave
                            usuarios.add(user)
                        }

                        Log.d("MPS", "$usuarios")
                        success(usuarios)

                    } else {

                        Log.e("MPS", "Ocurrió un error (${response.code()}) ${response.message()}")
                        error(response.message())

                    }
                }

                override fun onFailure(call: Call<Map<String, UsuarioDTO>>, t: Throwable) {

                    Log.e("MPS", "Ocurrió un error... ${t.message}")
                    error(t.message.toString())

                }

            })
    }

    fun createUser(
        newUser: UsuarioDTO,
        success: (String) -> Unit,
        error: (String) -> Unit
    ) {

        this.userWSClient.postUser(newUser).enqueue(
            object : Callback<Map<String, String>> {
                override fun onResponse(
                    call: Call<Map<String, String>>,
                    response: Response<Map<String, String>>
                ) {

                    if (response.isSuccessful) {

                        val responseMap = response.body()!!
                        success(responseMap["name"]!!)

                    } else {
                        error(response.message())
                    }

                }

                override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                    error(t.message.toString())
                }
            }
        )
    }

    fun updateUser(
        usuario: UsuarioDTO,
        success: (UsuarioDTO) -> Unit,
        error: (String) -> Unit
    ) {

        this.userWSClient.putUser(usuario, usuario.id!!).enqueue(
            object : Callback<UsuarioDTO> {
                override fun onResponse(
                    call: Call<UsuarioDTO>,
                    response: Response<UsuarioDTO>
                ) {

                    if (response.isSuccessful) {
                        Log.i("MPS", "Se actualizó el usuario: ${response.body()}")
                        success(response.body()!!)
                    } else {
                        Log.e("MPS", "Ocurrió un error al actualizar: ${response.message()}")
                        error(response.message())
                    }

                }

                override fun onFailure(call: Call<UsuarioDTO>, t: Throwable) {
                    error(t.message.toString())
                }
            })

    }

    fun deleteUser(
        userId: String,
        success: () -> Unit,
        error: (String) -> Unit
    ) {

        this.userWSClient.deleteUser(userId).enqueue(
            object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {

                    if (response.isSuccessful) {
                        success()
                    } else {
                        error(response.message())
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    error("Ocurrió un error: ${t.message}")
                }

            }
        )
    }
}