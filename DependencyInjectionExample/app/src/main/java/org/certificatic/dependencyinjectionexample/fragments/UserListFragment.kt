package org.certificatic.dependencyinjectionexample.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import org.certificatic.dependencyinjectionexample.R
import org.certificatic.dependencyinjectionexample.adapter.UserListAdapter
import org.certificatic.dependencyinjectionexample.dto.UsuarioDTO
import org.certificatic.dependencyinjectionexample.services.UserServiceWS
import javax.inject.Inject


@AndroidEntryPoint
class UserListFragment : Fragment() {

    @Inject
    lateinit var userService: UserServiceWS

    private lateinit var lvUsers: ListView
    private lateinit var lavLoading: LottieAnimationView
    private lateinit var srlSwipe: SwipeRefreshLayout

    //Primer método (después del constructor) en ser ejecutado
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: Configuración "lógica" del fragment
        // Inicialización de controladores
        //  Configurar/inyectar una referencia
    }

    //Inflar la vista a mostrar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_user_list,
            container,
            false   // 'false' permite remover la vista de la UI
            // 'true' deja "fija" la vista en la UI
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Configuración "gráfica" del fragment
        // consulta de la lista de usuarios a un 'servicio' (BD)

        //Consumo BD
//        val userService = UserService.instance
//        val listUsers = userService.getUsers()

        this.lavLoading = view.findViewById<LottieAnimationView>(R.id.lavLoading)

        this.lvUsers = view.findViewById<ListView>(R.id.lvUsers)

        this.srlSwipe = view.findViewById<SwipeRefreshLayout>(R.id.srlSwipe)
        srlSwipe.setOnRefreshListener {
            getAllUsers()
        }

        lvUsers.setOnItemClickListener { listView, view, index, hash ->

            //TODO Arreglar este cast que falla
            val user = listView.adapter.getItem(index) as UsuarioDTO
            Log.d("MPS", "Usuario seleccionado: $user")

            val userDetail = UserDetailFragment.newInstance(user)

            val trans = this.requireActivity().supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainer, userDetail)
            trans.addToBackStack(null)

            trans.commit()

        }

        //Botón para mostrar formulario de usuarios
        val fabCrear = view.findViewById<FloatingActionButton>(R.id.fabCrear)
        fabCrear.setOnClickListener {

            val createUserFragment = CreateUserFragment.newInstance()

            val trans = this.requireActivity().supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainer, createUserFragment)
            trans.addToBackStack("UserListFragment")

            trans.commit()

        }

        getAllUsers()

    }

    private fun getAllUsers() {

        lavLoading.visibility = View.VISIBLE

        //Consumo WS
        this.userService.getAllUsers(
            { usuarios ->

                lvUsers.adapter = UserListAdapter(usuarios)

                lavLoading.visibility = View.INVISIBLE
                srlSwipe.isRefreshing = false

            },
            { error ->
                Log.e("MPS", "Ocurrió un error al consumir el WS ($error)")

                lavLoading.visibility = View.INVISIBLE
                srlSwipe.isRefreshing = false
            })

    }

    //Aqui pondríamos "propiedades" o "métods" estáticos
    //Miembros accesibles desde la CLASE
    companion object {

        @JvmStatic
        fun newInstance() = UserListFragment()

    }
}