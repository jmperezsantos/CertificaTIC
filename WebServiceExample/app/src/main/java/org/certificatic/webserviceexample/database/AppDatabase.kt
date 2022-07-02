package org.certificatic.webserviceexample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.certificatic.webserviceexample.model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}