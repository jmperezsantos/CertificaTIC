package org.certificatic.navigationexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etMessage = findViewById<EditText>(R.id.etMensaje)

        val fabNavega = findViewById<FloatingActionButton>(R.id.fabNavega)
        fabNavega.setOnClickListener {

            Log.d("MPS", "Navegando a segunda actividad")

            val intent = Intent(
                this,
                SecondActivity::class.java
            )

            intent.putExtra(
                SecondActivity.MESSAGE_ARG,
                etMessage.text.toString()
            )

            val theBundle = Bundle()
            theBundle.putInt("UN_ENTERO", 5)
            theBundle.putString("UN_STRING", "Hola Mundo")
            theBundle.putFloat("UN_FLOTANTE", 5.5f)

            intent.putExtra("TODOS_DATOS", theBundle)

            startActivity(intent)

        }
    }
}