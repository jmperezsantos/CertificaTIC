package org.certificatic.navigationexamplefragments.services

import org.certificatic.navigationexamplefragments.model.UserModel

class UserService {

    fun getUsers(): List<UserModel> {
        //TODO: llamada a WS/BD

        val userList = mutableListOf<UserModel>()

        for (i in 1..20) {
            userList.add(
                UserModel(
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