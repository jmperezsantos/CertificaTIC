package org.certificatic.collectionsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewExample = findViewById<ListView>(R.id.lvUsers)

        val laLista = createUsers(20)
        listViewExample.adapter = UsersListViewAdapter(laLista)

        listViewExample.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, index, itemId ->

            val adapter = adapterView.adapter
            val user = adapter.getItem(index) as UserEntity
            Log.d("MPS", "Se presionó la posición " + index + " Usuario es: " + user.lastname)

            //Mostrar una siguiente pantalla con la entidad/objeto seleccionado


//            val usuario = laLista[index]
//
//            Toast.makeText(this,
//                "Se presionó la posición " + index,
//                Toast.LENGTH_LONG).show()
//
//            Log.d("MPS", "Se presionó la posición " + index + " Usuario es: " + usuario.lastname)

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
                    i * i,
                    true
                )
            )
        }
        return userList
    }
}