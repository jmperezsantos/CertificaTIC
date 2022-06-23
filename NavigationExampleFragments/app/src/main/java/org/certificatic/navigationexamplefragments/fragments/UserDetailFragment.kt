package org.certificatic.navigationexamplefragments.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import org.certificatic.navigationexamplefragments.R
import org.certificatic.navigationexamplefragments.model.UserModel


/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDetailFragment : Fragment() {

    //Para acomodar miembros (métodos/propiedades) estáticos
    companion object {

        @JvmStatic
        fun newInstance(user: UserModel): UserDetailFragment {

            return UserDetailFragment().apply {
                arguments = Bundle().apply {

                    putSerializable("USER_ARG", user)

                }
            }
        }
    }

    //Propiedades del Fragment
    private lateinit var user: UserModel

    //Configuración "Lógica"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.arguments?.let { it ->

            this.user = it.getSerializable("USER_ARG") as UserModel
            Log.d("MPS", "Llegó: $user")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_user_detail,
            container,
            false
        )
    }

    //Configuración de UI
    override fun onViewCreated(
        view: View, //Aqui vive la interfaz gráfica.
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Obtener las referencias de TextViews de la UI
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvLastname = view.findViewById<TextView>(R.id.tvLastname)
        val tvAge = view.findViewById<TextView>(R.id.tvEdad)
        val sActivo = view.findViewById<Switch>(R.id.swActive)

        //TODO: Configurar la vista
        tvName.text = this.user.name
        tvLastname.text = this.user.lastname
        tvAge.text = "${this.user.age}"

        sActivo.isChecked = this.user.active

    }

}