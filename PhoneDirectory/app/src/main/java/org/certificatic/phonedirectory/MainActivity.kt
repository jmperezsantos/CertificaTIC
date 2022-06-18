package org.certificatic.phonedirectory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewExample = findViewById<ListView>(R.id.lvUsers)

        val laLista = createUsers(20)
        listViewExample.adapter = UsersListViewAdapter(laLista)

        listViewExample.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, index, itemId ->

                val adapter = adapterView.adapter
                val user = adapter.getItem(index) as UserEntity
                Log.d("MPS", "Se presionó la posición " + index + " Usuario es: " + user.lastname)

                val intent = Intent(this, UserDetailActivity::class.java)

                //TODO Mostrar una siguiente pantalla con la entidad/objeto seleccionado
                intent.putExtra("USER", user)

                startActivity(intent)

            }

    }

    //Crea usuarios "dinámicamente"
    private fun createUsers(count: Int): List<UserEntity> {

        var userList = arrayListOf<UserEntity>()

        for (i in 1..count) {
            userList.add(
                UserEntity(
                    i,
                    "Name $i",
                    "Lastname $i",
                    i,
                    i % 3 == 0
                )
            )
        }
        return userList
    }
}