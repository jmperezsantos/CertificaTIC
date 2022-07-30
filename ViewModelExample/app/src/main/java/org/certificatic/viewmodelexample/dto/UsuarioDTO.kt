package org.certificatic.viewmodelexample.dto

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import org.certificatic.viewmodelexample.R
import java.io.Serializable

data class UsuarioDTO(
    //Se ignora la propiedad al momento de generar el JSON
    @Transient var id: String? = null,
    var activo: Boolean,
    var apellido: String,
    var edad: Int,
    var nombre: String,
    var foto: String? = null
) : Serializable {

    val fotoBitmap: Bitmap?
        get() {

            return if (this.foto != null) {
                val bytes = Base64.decode(this.foto, Base64.DEFAULT)
                BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            } else {
                null
            }

        }

    override fun toString(): String {
        return "UsuarioDTO(id=$id, activo=$activo, apellido='$apellido', edad=$edad, nombre='$nombre')"
    }
}