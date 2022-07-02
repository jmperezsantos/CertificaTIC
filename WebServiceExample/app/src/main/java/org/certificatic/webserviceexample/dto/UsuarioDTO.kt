package org.certificatic.webserviceexample.dto

data class UsuarioDTO(
    //Se ignora la propiedad al momento de generar el JSON
    @Transient var id: String? = null,
    var activo: Boolean,
    var apellido: String,
    var edad: Int,
    var nombre: String
) {

    override fun toString(): String {
        return "UsuarioDTO(id=$id, activo=$activo, apellido='$apellido', edad=$edad, nombre='$nombre')"
    }
}