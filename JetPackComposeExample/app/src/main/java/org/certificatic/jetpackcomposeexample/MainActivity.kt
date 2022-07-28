package org.certificatic.jetpackcomposeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.certificatic.jetpackcomposeexample.ui.theme.JetPackComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                //createBody("Manuel")
                createTexts()
            }
        }
    }
}

@Composable
fun createTexts() {

    var textoValor by remember {
        mutableStateOf("Hola")
    }

    Column() {

        Text(text = textoValor)
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textoValor,
            onValueChange = { texto ->
                textoValor = texto
                Log.d("MPS", "Texto: $texto")
            })

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                Log.i("MPS", "El Texto del TextField es: $textoValor")
            }) {

            Text(text = "Presioname!")

        }

        LazyColumn() {

            //userList

            items(5) { index ->

                getItem("$index")

            }

        }

    }
}

@Composable
fun getItem(value: String) {

    Row() {

        Image(
            painter = painterResource(R.drawable.user_placeholder),
            contentDescription = "Placeholder de un usuario"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column() {
            Text("Nombre: $value")

            Text("Apellido: $value")
        }

    }

}

@Composable
fun createBody(name: String) {

    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(//LinearLayout orientation->horizontal
            modifier = Modifier.fillMaxWidth(),//match_parent
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Hello $name!")
            Text(text = "Otro campo")
            Text(text = "Otro campo")
        }

        Column(//LinearLayout orientation->vertical
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Hello $name!")
            Text(text = "Otro campo")
            Text(text = "Otro campo")
        }

        Row() {

            Image(
                painter = painterResource(R.drawable.user_placeholder),
                contentDescription = "Placeholder de un usuario"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column() {
                Text("Nombre")

                Text("Apellido")
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackComposeExampleTheme {
        createBody("Android")
    }
}