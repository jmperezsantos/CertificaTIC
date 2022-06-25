package com.example.databaseexample.services

import androidx.room.Room
import com.example.databaseexample.MainActivity
import com.example.databaseexample.database.AppDatabase
import com.example.databaseexample.database.UserDao
import com.example.databaseexample.model.UserModel

class UserService {

    //Ponemos miembros estáticos
    companion object {

        var instance: UserService = UserService()

    }

    //DAO para acceder a Base de Datos
    private val userDao: UserDao

    //La MutableList permite "modificar" el contenido de la lista.
    //private var userList: MutableList<UserModel>

    private constructor() {
        //this.userList = mutableListOf<UserModel>()

        val database = Room.databaseBuilder(
            MainActivity.theContext,
            AppDatabase::class.java,
            "user-agenda"
        )
            .allowMainThreadQueries()
            .build()

        this.userDao = database.userDao()

    }

    fun getUsers(): List<UserModel> {
        //return userList
        return this.userDao.getAll()
    }

    fun addUser(user: UserModel) {
        //this.userList.add(user)
        this.userDao.insertAll(user)
    }

    fun deleteUser(id: Int) {

        //val userToDelete = this.userList.filter { userModel -> userModel.uid == id }[0]
        //this.userList.remove(userToDelete)

        val userToDelete = this.userDao.findById(id)
        this.userDao.delete(userToDelete)

        //this.userList.removeIf { userModel -> userModel.id == id }

    }

    fun updateUser(userToUpdate: UserModel) {

        /*val originalUser =
            this.userList.filter { userModel -> userModel.uid == userToUpdate.uid }[0]

        originalUser.name = userToUpdate.name
        originalUser.lastname = userToUpdate.lastname
        originalUser.age = userToUpdate.age
        originalUser.active = userToUpdate.active
        */

        this.userDao.update(userToUpdate)

    }

}