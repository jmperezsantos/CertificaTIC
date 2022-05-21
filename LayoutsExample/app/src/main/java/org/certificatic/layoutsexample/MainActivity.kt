package org.certificatic.layoutsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    //Lo primero que se ejecuta al correr este proceso
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Se asigna la UI (User Interface) a la actividad
        setContentView(R.layout.activity_layout_relative2)

    }
}