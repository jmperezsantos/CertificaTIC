package org.certificatic.webserviceexample.wsclient

import org.certificatic.webserviceexample.dto.UsuarioDTO
import retrofit2.Call
import retrofit2.http.*

interface UserWSClient {

//    @GET("usuarios.json")
//    fun getAllUsers(): Call<List<UsuarioDTO>>

    //Obtener todos los usuarios
    @GET("usuarios.json")
    fun getAllUsers(): Call<
            Map<String, UsuarioDTO>
            >

    //Registrar un nuevo usuario
    @POST("usuarios.json")
    fun postUser(
        @Body usuario: UsuarioDTO
    ): Call<Map<String, String>>

    //Actualizar un usuario
    @PUT("usuarios/{usuarioId}.json")
    fun putUser(
        @Body usuario: UsuarioDTO,
        @Path("usuarioId") usuarioId: String
    ): Call<UsuarioDTO>

    //Borrar un registro
    @DELETE("usuarios/{usuarioId}.json")
    fun deleteUser(
        @Path("usuarioId") usuarioId: String
    ): Call<Void>

}