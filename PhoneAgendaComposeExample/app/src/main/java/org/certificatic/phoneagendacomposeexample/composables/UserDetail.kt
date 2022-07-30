package org.certificatic.phoneagendacomposeexample.composables

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.certificatic.phoneagendacomposeexample.R
import org.certificatic.phoneagendacomposeexample.dto.UsuarioDTO

@Composable
fun UserDetail(user: UsuarioDTO) {
    val usuario by remember {
        mutableStateOf(user)
    }

    Scaffold() {
        Column(
            Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                usuario.foto?.also { foto ->

                    val base64 = Base64.decode(foto, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(base64, 0, base64.size)

                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "user foto",
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                    )

                } ?: run {
                    Image(
                        painter = painterResource(id = R.drawable.user_placeholder),
                        contentDescription = "Placeholder",
                        modifier = Modifier.size(200.dp, 200.dp)
                    )
                }
            }

            TextField(
                value = usuario.nombre,
                onValueChange = {
                    usuario.nombre = it
                }, modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = usuario.apellido,
                onValueChange = {
                    usuario.apellido = it
                }, modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = "${usuario.edad}",
                onValueChange = {
                    usuario.edad = it.toInt()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ), modifier = Modifier.fillMaxWidth()
            )

        }

    }

}