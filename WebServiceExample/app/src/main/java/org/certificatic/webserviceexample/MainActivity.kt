package org.certificatic.webserviceexample

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.certificatic.webserviceexample.fragments.UserListFragment
import org.certificatic.webserviceexample.services.UserServiceWS

class MainActivity : AppCompatActivity() {

    //Propiedades "estáticas"
    companion object {

        lateinit var theContext: Context

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        theContext = this

        //Generar instancia del Fragment a mostrar
        val userListFragment = UserListFragment.newInstance()

        //Inicializar una transacción (reemplazar el fragment)
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, userListFragment)

        //Confirmación de la transacción
        transaction.commit()

    }

}