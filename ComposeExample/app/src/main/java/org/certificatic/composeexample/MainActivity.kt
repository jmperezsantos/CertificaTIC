package org.certificatic.composeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.certificatic.composeexample.dto.MessageDTO
import org.certificatic.composeexample.ui.theme.ComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme() {
                CreateMessageCard(MessageDTO("Manuel Pérez", "Hola Mundo!"))
            }
        }
    }

    @Composable
    fun CreateMessageCard(message: MessageDTO) {
        Column() {

            Row {

                Image(
                    painter = painterResource(id = R.drawable.jetpack),
                    contentDescription = "Imagen",
                    Modifier
                        .size(50.dp, 50.dp)
                        .border(1.5.dp, MaterialTheme.colors.secondary)
                )

                Column() {
                    Text(message.author, color = MaterialTheme.colors.primaryVariant)

                    Text(message.body, color = MaterialTheme.colors.secondaryVariant)
                }

            }

            Button(onClick = {
                Log.i("MPS", "Botón Presionado!!")
            }) {

                Image(
                    painter = painterResource(R.drawable.jetpack),
                    contentDescription = "",
                    Modifier.size(50.dp, 50.dp)
                )

                Text(text = "Presioname!!!")

            }

        }

    }

    @Preview
    @Composable
    fun previewMainActivity() {

        ComposeExampleTheme {
            CreateMessageCard(MessageDTO("Manuel Pérez", "Hola Mundo!"))
        }

    }
}
