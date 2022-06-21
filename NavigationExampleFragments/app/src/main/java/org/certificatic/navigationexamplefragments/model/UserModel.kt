package org.certificatic.navigationexamplefragments.model

class UserModel(
    val name: String,
    val lastname: String,
    val age: Int,
    val active: Boolean
) {

    override fun toString(): String {
        return "UserModel(name='$name', lastname='$lastname', age=$age, active=$active)"
    }
}