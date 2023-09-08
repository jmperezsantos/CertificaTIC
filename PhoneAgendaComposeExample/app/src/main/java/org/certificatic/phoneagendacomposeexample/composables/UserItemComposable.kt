package org.certificatic.phoneagendacomposeexample.composables

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.certificatic.phoneagendacomposeexample.R
import org.certificatic.phoneagendacomposeexample.dto.UsuarioDTO

@Composable
fun UserItem(
    user: UsuarioDTO,
    onItemClick: (UsuarioDTO) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onItemClick(user)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        user.foto?.also { foto ->

            val base64 = Base64.decode(foto, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(base64, 0, base64.size)

            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "user foto",
                modifier = Modifier.size(75.dp, 75.dp)
            )

        } ?: run {
            Image(
                painter = painterResource(id = R.drawable.user_placeholder),
                contentDescription = "Placeholder",
                modifier = Modifier.size(75.dp, 75.dp)
            )
        }

        Column() {
            Text(text = user.nombre)
            Text(text = user.apellido)
        }

        Spacer(modifier = Modifier.weight(1f))

        Checkbox(
            checked = user.activo == 1,
            onCheckedChange = null
        )

    }

}