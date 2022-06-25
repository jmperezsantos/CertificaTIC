package com.example.databaseexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "lastname") var lastname: String,
    @ColumnInfo(name = "age") var age: Int,
    @ColumnInfo(name = "active") var active: Boolean
) : Serializable {

    override fun toString(): String {
        return "UserModel(id=$uid, name='$name', lastname='$lastname', age=$age, active=$active)"
    }
}