package org.certificatic.navigationexamplefragments.model

import java.io.Serializable

class UserModel(
    val name: String,
    val lastname: String,
    val age: Int,
    val active: Boolean
) : Serializable {

    override fun toString(): String {
        return "UserModel[name='$name', lastname='$lastname', age=$age, active=$active]"
    }
}