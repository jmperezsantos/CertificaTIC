package org.certificatic.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.certificatic.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        //New Way
        viewBinding.tvTitle.text = "Cambiamos el Título con ViewBinding"
        viewBinding.btnAcceder.setOnClickListener {
            Toast.makeText(this, "Presionaron desde ViewBinding", Toast.LENGTH_LONG).show()
        }
        viewBinding.ivPlaneta.setImageResource(R.drawable.otro_planeta)

        //Old Way
//        val etUsername = findViewById<EditText>(R.id.etUsername)
//        val tvTitle = findViewById<TextView>(R.id.tvTitle)
//        etUsername.setText("Hola Mundo")
//        tvTitle.text = "Cambiamos el título"

    }
}