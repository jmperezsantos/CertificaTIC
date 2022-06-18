package org.certificatic.navigationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondActivity : AppCompatActivity() {

    companion object {
        val MESSAGE_ARG: String = "MESSAGE_ARGUMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //Rescatar mensaje
        val message = this.intent.getStringExtra(MESSAGE_ARG)
        Log.d("MPS", "Se recibió el mensaje: $message")

        val theBundle = this.intent.getBundleExtra("TODOS_DATOS")
        

        val fabAtras = findViewById<FloatingActionButton>(R.id.fabAtras)
        fabAtras.setOnClickListener {

            //Se termina el proceso de la actividad.
            finish()

        }

    }
}