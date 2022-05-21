package org.certificatic.collectionsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewExample = findViewById<ListView>(R.id.lvUsers)

        listViewExample.adapter = UsersListViewAdapter(
            createUsers(20)
        )

    }

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