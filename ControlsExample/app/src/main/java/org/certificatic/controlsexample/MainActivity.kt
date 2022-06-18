package org.certificatic.controlsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val textView = findViewById<TextView>(R.id.tvTextView)
//        val editText = findViewById<EditText>(R.id.etEditText)
//        val button = findViewById<Button>(R.id.btnButton)
//
//        button.setOnClickListener {
//            textView.text = editText.text
//            editText.text.clear()
//        }
//
//        val sSwitch1 = findViewById<Switch>(R.id.sSwitch1)
//        sSwitch1.setOnCheckedChangeListener { theSwitch, state ->
//            Log.d("MPS", "El switch está: " + state)
//            editText.isEnabled = state
//
//            if (state) {
//                theSwitch.text = "Encendido"
//            } else {
//                theSwitch.text = "Ahora no puedes escribir"
//            }
//        }
//
//        val cbCheckBox1 = findViewById<CheckBox>(R.id.cbCheckBox1)
//        cbCheckBox1.setOnCheckedChangeListener { theCheck, checked ->
//
//            Log.d("MPS", "El CheckBox está: $checked")
//
//            if (checked) {
//                button.text = "Puedes Presionar!"
//            } else {
//                button.text = "NO puedes presionar"
//            }
//
//            button.isEnabled = checked
//
//        }
//
//        val tvComodin = findViewById<TextView>(R.id.tvComodin)
//        val rgRadioGroup = findViewById<RadioGroup>(R.id.rgRadioGroupOpciones)
//
//        val radioDinamico = RadioButton(this)
//        radioDinamico.text = "Este es un Radio Dinamico"
//        radioDinamico.id = 123
//
//        val radioDinamico2 = RadioButton(this)
//        radioDinamico2.text = "Este es un Radio Dinamico2"
//        radioDinamico2.id = 124
//
//        rgRadioGroup.addView(radioDinamico)
//        rgRadioGroup.addView(radioDinamico2)
//
//        rgRadioGroup.setOnCheckedChangeListener { radioGroup, radioButtonId ->
//
//            when (radioButtonId) {
//                R.id.rbOpc1 -> {
//                    Log.d("MPS", "Se seleccionó el Radio: Opción 1")
//
//                    tvComodin.text = "Opcion 1"
//                }
//                R.id.rbOpc2 -> {
//                    Log.d("MPS", "Se seleccionó el Radio: Opción 2")
//                    tvComodin.text = "Opcion 2"
//                }
//                R.id.rbOpc3 -> {
//                    Log.d("MPS", "Se seleccionó el Radio: Opción 3")
//                    tvComodin.text = "Opcion 3"
//                }
//                R.id.rbOpcN -> {
//                    Log.d("MPS", "Se seleccionó el Radio: Opción N")
//                    tvComodin.text = "Opcion 4"
//                }
//                123 -> {
//                    Log.d("MPS", "Se seleccionó el Radio: Opción Dinamica 1")
//                    tvComodin.text = "Opcion Dinamica 1"
//                }
//                124 -> {
//                    Log.d("MPS", "Se seleccionó el Radio: Opción Dinamica 2")
//                    tvComodin.text = "Opcion Dinamica 2"
//                }
//            }
//        }
//
//        val sSlider = findViewById<Slider>(R.id.sSlider)
//        sSlider.addOnChangeListener { slider, value, fromUser ->
//
//            Log.d("MPS", "El valor del slider es: $value")
//            val theValue : Int = (value*100).toInt()
//            tvComodin.text = "Valor $theValue%"
//
//        }

        val fabWalter = findViewById<FloatingActionButton>(R.id.fabWalter)
        val progress = findViewById<ProgressBar>(R.id.pbProgress)
        fabWalter.setOnClickListener {

            if(progress.visibility == View.VISIBLE) {
                progress.visibility = View.INVISIBLE
            } else {
                progress.visibility = View.VISIBLE
            }

        }

    }
}