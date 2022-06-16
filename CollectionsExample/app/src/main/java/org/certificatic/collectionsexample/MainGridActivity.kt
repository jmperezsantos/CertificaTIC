package org.certificatic.collectionsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.GridView

class MainGridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_grid)

        val gridView = findViewById<GridView>(R.id.gvUsers)

        //TODO:
        //if (portrait) => 3
        //if (landscape) => 5
        //if(tablet)=>10
        gridView.numColumns = 3

        gridView.adapter = UsersGridViewAdapter(
            createUsers(100)
        )

        gridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, index, hash ->

                val usuario = parent.adapter.getItem(index) as UserEntity
                Log.d("MPS", "Usuario seleccionado: " + usuario.lastname)

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