package org.certificatic.viewmodelexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.certificatic.viewmodelexample.dto.UsuarioDTO
import org.certificatic.viewmodelexample.R

class UserListAdapter(private val users: List<UsuarioDTO>) : BaseAdapter() {

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

        val currentUser = this.getItem(index) as UsuarioDTO

        val tvName = holder!!.findViewById<TextView>(R.id.tvName)
        val tvLastname = holder!!.findViewById<TextView>(R.id.tvLastname)

        tvName.text = currentUser.nombre
        tvLastname.text = currentUser.apellido

        return holder!!

    }
}