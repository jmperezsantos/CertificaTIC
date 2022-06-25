package org.certificatic.navigationexamplefragments.model

import java.io.Serializable

class UserModel(
    val id: Int,
    var name: String,
    var lastname: String,
    var age: Int,
    var active: Boolean
) : Serializable {

    override fun toString(): String {
        return "UserModel(id=$id, name='$name', lastname='$lastname', age=$age, active=$active)"
    }
}