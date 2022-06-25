package org.certificatic.navigationexamplefragments.services

import org.certificatic.navigationexamplefragments.model.UserModel

class UserService {

    //Ponemos miembros estáticos
    companion object {

        var instance: UserService = UserService()

    }

    //La MutableList permite "modificar" el contenido de la lista.
    private var userList: MutableList<UserModel>

    private constructor() {
        this.userList = mutableListOf<UserModel>()

        for (i in 1..5) {
            userList.add(
                UserModel(
                    i,
                    "Name $i",
                    "Lastname $i",
                    i * i,
                    true
                )
            )
        }
    }

    fun getUsers(): List<UserModel> {
        return userList
    }

    fun addUser(user: UserModel) {
        this.userList.add(user)
    }

    fun deleteUser(id: Int) {

        val userToDelete = this.userList.filter { userModel -> userModel.id == id }[0]

        this.userList.remove(userToDelete)

        //this.userList.removeIf { userModel -> userModel.id == id }

    }

    fun updateUser(userToUpdate: UserModel) {

        val originalUser = this.userList.filter { userModel -> userModel.id == userToUpdate.id }[0]

        originalUser.name = userToUpdate.name
        originalUser.lastname = userToUpdate.lastname
        originalUser.age = userToUpdate.age
        originalUser.active = userToUpdate.active

    }

}