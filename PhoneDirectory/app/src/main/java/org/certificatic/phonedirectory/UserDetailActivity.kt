package org.certificatic.phonedirectory

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class UserDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        //TODO 1: Rescatar el objeto enviado
        val user = this.intent.getSerializableExtra("USER") as UserEntity

        //TODO 2: Mostrar la información en la UI
        val tvUser = findViewById<TextView>(R.id.tvName)
        val tvLastname = findViewById<TextView>(R.id.tvLastname)
        val tvAge = findViewById<TextView>(R.id.tvAge)
        val cbActive = findViewById<CheckBox>(R.id.cbIsActive)
        val sAge = findViewById<Slider>(R.id.sAge)

        tvUser.text = user.name
        tvLastname.text = user.lastname
        tvAge.text = "Edad: ${user.age}"
        cbActive.isChecked = user.isActive

        sAge.value = (user.age.toFloat() / 10)

    }
}