package org.certificatic.controlsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tvTextView)
        val editText = findViewById<EditText>(R.id.etEditText)
        val button = findViewById<Button>(R.id.btnButton)

        button.setOnClickListener {
            textView.text = editText.text
            editText.text.clear()
        }

        val sSwitch1 = findViewById<Switch>(R.id.sSwitch1)
        sSwitch1.setOnCheckedChangeListener { theSwitch, state ->
            Log.d("MPS", "El switch está: " + state)
            editText.isEnabled = state

            if (state) {
                theSwitch.text = "Encendido"
            } else {
                theSwitch.text = "Ahora no puedes escribir"
            }
        }

    }
}