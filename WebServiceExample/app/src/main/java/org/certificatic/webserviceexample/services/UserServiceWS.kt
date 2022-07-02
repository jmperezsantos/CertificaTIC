package org.certificatic.webserviceexample.services

import android.util.Log
import org.certificatic.webserviceexample.dto.UsuarioDTO
import org.certificatic.webserviceexample.wsclient.UserWSClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class UserServiceWS {

    companion object {

        val instance: UserServiceWS = UserServiceWS()

    }

    private var userWSClient: UserWSClient

    private constructor() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://certificatic-wsexample-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.userWSClient = retrofit.create(UserWSClient::class.java)

    }

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
}