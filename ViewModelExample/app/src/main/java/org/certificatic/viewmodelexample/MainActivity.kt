package org.certificatic.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.certificatic.viewmodelexample.fragments.UserListFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Generar instancia del Fragment a mostrar
        val userListFragment = UserListFragment.newInstance()

        //Inicializar una transacción (reemplazar el fragment)
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, userListFragment)

        //Confirmación de la transacción
        transaction.commit()

    }

}