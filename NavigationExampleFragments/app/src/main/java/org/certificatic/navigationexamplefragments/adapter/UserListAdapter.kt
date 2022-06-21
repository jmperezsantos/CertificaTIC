package org.certificatic.navigationexamplefragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.certificatic.navigationexamplefragments.R
import org.certificatic.navigationexamplefragments.model.UserModel

class UserListAdapter(val users: List<UserModel>) : BaseAdapter() {

    override fun getCount(): Int {
        return this.users.size
    }

    override fun getItem(index: Int): Any {
        return this.users[index]
    }

    override fun getItemId(index: Int): Long {
        return this.getItem(index).hashCode().toLong()
    }

    override fun getView(
        index: Int,
        reusableView: View?,
        parent: ViewGroup?
    ): View {

        var holder = reusableView
        if (holder == null) {

            val inflater = LayoutInflater.from(parent!!.context)
            holder = inflater.inflate(
                R.layout.user_list_item,
                parent,
                false
            )

        }

        val currentUser = this.getItem(index) as UserModel

        val tvName = holder!!.findViewById<TextView>(R.id.tvName)
        val tvLastname = holder!!.findViewById<TextView>(R.id.tvLastname)

        tvName.text = currentUser.name
        tvLastname.text = currentUser.lastname

        return holder!!

    }
}