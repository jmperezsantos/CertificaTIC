package org.certificatic.webserviceexample.database

import androidx.room.*
import org.certificatic.webserviceexample.model.UserModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<UserModel>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<UserModel>

    @Query("SELECT * FROM user WHERE uid == :uid")
    fun findById(uid: Int): UserModel

    @Query(
        "SELECT * FROM user WHERE name LIKE :first AND " +
                "lastname LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): UserModel

    @Insert
    fun insertAll(vararg users: UserModel)

    @Delete
    fun delete(user: UserModel)

    @Update
    fun update(user: UserModel)
}