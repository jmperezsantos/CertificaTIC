package org.certificatic.phoneagendacomposeexample.wsclient

import org.certificatic.phoneagendacomposeexample.dto.UsuarioDTO
import retrofit2.Call
import retrofit2.http.*

interface UserWSClient {

//    @GET("usuarios.json")
//    fun getAllUsers(): Call<List<UsuarioDTO>>

    //Obtener todos los usuarios
    @GET("users.json")
    fun getAllUsers(): Call<
            Map<String, UsuarioDTO>
            >

    //Registrar un nuevo usuario
    @POST("users.json")
    fun postUser(
        @Body usuario: UsuarioDTO
    ): Call<Map<String, String>>

    //Actualizar un usuario
    @PUT("users/{usuarioId}.json")
    fun putUser(
        @Body usuario: UsuarioDTO,
        @Path("usuarioId") usuarioId: String
    ): Call<UsuarioDTO>

    //Borrar un registro
    @DELETE("users/{usuarioId}.json")
    fun deleteUser(
        @Path("usuarioId") usuarioId: String
    ): Call<Void>

}