package org.certificatic.phoneagendacomposeexample.dto

import com.google.gson.annotations.SerializedName

data class UsuarioDTO(
    @Transient var id: String? = null,
    @SerializedName("status") var activo: Int,
    @SerializedName("lastname") var apellido: String,
    @SerializedName("age") var edad: Int? = null,
    @SerializedName("name") var nombre: String,
    @SerializedName("image") var foto: String? = null
) {

    override fun toString(): String {
        return "UsuarioDTO(id=$id, activo=$activo, apellido='$apellido', edad=$edad, nombre='$nombre')"
    }
}