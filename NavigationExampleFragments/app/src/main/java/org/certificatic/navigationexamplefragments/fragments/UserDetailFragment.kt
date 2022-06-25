package org.certificatic.navigationexamplefragments.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import org.certificatic.navigationexamplefragments.R
import org.certificatic.navigationexamplefragments.model.UserModel
import org.certificatic.navigationexamplefragments.services.UserService


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

        //Declaración de variables
        val tvName = view.findViewById<EditText>(R.id.tvName)
        val tvLastname = view.findViewById<EditText>(R.id.tvLastname)
        val tvAge = view.findViewById<EditText>(R.id.tvEdad)
        val sActivo = view.findViewById<Switch>(R.id.swActive)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)
        val btnEditar = view.findViewById<Button>(R.id.btnEditar)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)

        btnDelete.setOnClickListener {

            //Se borra al usuario de la lista, por medio del servicio
            UserService.instance.deleteUser(this.user.id)

            //Se regresa a la lista de usuarios.
            this.requireActivity().supportFragmentManager.popBackStack()

        }

        btnEditar.setOnClickListener {

            tvName.isEnabled = true
            tvLastname.isEnabled = true
            tvAge.isEnabled = true
            sActivo.isEnabled = true

            tvName.requestFocus(0)
            val inputManager = this.requireContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(tvName, InputMethodManager.SHOW_IMPLICIT)

            btnEditar.visibility = View.GONE
            btnGuardar.visibility = View.VISIBLE

        }

        btnGuardar.setOnClickListener {

            this.user.name = tvName.text.toString()
            this.user.lastname = tvLastname.text.toString()
            this.user.age = Integer.parseInt(tvAge.text.toString())
            this.user.active = sActivo.isChecked

            UserService.instance.updateUser(this.user)

            this.requireActivity().supportFragmentManager.popBackStack()

        }

        //TODO: Configurar la vista
        tvName.setText(this.user.name)
        tvLastname.setText(this.user.lastname)
        tvAge.setText("${this.user.age}")

        sActivo.isChecked = this.user.active

    }

}