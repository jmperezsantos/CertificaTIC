package org.certificatic.viewmodelexample.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import org.certificatic.dependencyinjectionexample.fragments.CreateUserFragment
import org.certificatic.viewmodelexample.R
import org.certificatic.viewmodelexample.adapter.UserListAdapter
import org.certificatic.viewmodelexample.dto.UsuarioDTO
import org.certificatic.viewmodelexample.viewmodel.UserListViewModel
import javax.inject.Inject


@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var lvUsers: ListView
    private lateinit var lavLoading: LottieAnimationView
    private lateinit var srlSwipe: SwipeRefreshLayout

    private lateinit var viewModel: UserListViewModel

    //Aqui pondríamos "propiedades" o "métods" estáticos
    //Miembros accesibles desde la CLASE
    companion object {

        @JvmStatic
        fun newInstance() = UserListFragment()

    }

    //Primer método (después del constructor) en ser ejecutado
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: Configuración "lógica" del fragment
        // Inicialización de controladores
        //  Configurar/inyectar una referencia

        this.viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)

        this.viewModel.getUsers().observe(this) { users ->
            populateData(users)
        }
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

        this.lavLoading = view.findViewById(R.id.lavLoading)

        this.lvUsers = view.findViewById(R.id.lvUsers)

        this.srlSwipe = view.findViewById(R.id.srlSwipe)
        srlSwipe.setOnRefreshListener {
            getAllUsers()
        }

        lvUsers.setOnItemClickListener { listView, view, index, hash ->

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

        this.viewModel.loadUsers()

    }

    private fun populateData(users: List<UsuarioDTO>) {

        this.lvUsers.adapter = UserListAdapter(users)

        lavLoading.visibility = View.INVISIBLE
        srlSwipe.isRefreshing = false

    }

}