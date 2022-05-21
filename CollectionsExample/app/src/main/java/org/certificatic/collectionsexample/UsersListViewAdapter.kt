package org.certificatic.collectionsexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class UsersListViewAdapter(private val userList: List<UserEntity>) : BaseAdapter() {

    //Este método nos indica cuántos elementos se van a dibujar/renderizar en la ListView
    override fun getCount(): Int {
        return this.userList.count()
    }
    //Sintaxis Java
//    override public int getCount(){
//        return 10;
//    }

    //Obtiene de manera individual los elementos a ser renderizados, basado en un "index"
    override fun getItem(index: Int): Any {
        return this.userList.get(index)
    }
    //Sintaxis Java
    //override Object getItem(int position){
    // return "Elemento: " + index;
    //}

    //Obtiene el identificador "único" de los elementos renderizados
    override fun getItemId(index: Int): Long {
        return getItem(index).hashCode().toLong()
    }
    //Sintaxis Java
    //override long getItemId(int index){
    // return "algo".getHash();
    //}

    //Obtiene la vista a mostrar en la Colección
    override fun getView(index: Int, reusableView: View?, parent: ViewGroup?): View {

        //TODO validar el posible error al hacer cast con un null
        var vContent: View? = reusableView
        if (reusableView == null) {
            //Crear la vista
            val inflater = LayoutInflater.from(parent?.context)
            vContent = inflater.inflate(R.layout.user_item_layout, parent, false)
        }

        //Configurar la vista
        //Poner información en los campos de texto/imagenes/botones
        //Con base en el elemento en la posición 'index'

        val tvName = vContent!!.findViewById<TextView>(R.id.tvName)
        val tvLastname = vContent.findViewById<TextView>(R.id.tvLastname)

        val user = getItem(index) as UserEntity

        tvName.setText(user.name)
        tvLastname.text = user.lastname

        //Retornar la vista configurada
        return vContent
    }
    //Sintaxis Java
    //override View getView(int index, View reusableView, ViewGroup viewGroup){
    // ....
    //}
}