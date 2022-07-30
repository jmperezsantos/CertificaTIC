package org.certificatic.phoneagendacomposeexample.dto

data class UsuarioDTO(
    @Transient var id: String? = null,
    var activo: Boolean,
    var apellido: String,
    var edad: Int,
    var nombre: String,
    var foto: String? = null
) {

    override fun toString(): String {
        return "UsuarioDTO(id=$id, activo=$activo, apellido='$apellido', edad=$edad, nombre='$nombre')"
    }
}