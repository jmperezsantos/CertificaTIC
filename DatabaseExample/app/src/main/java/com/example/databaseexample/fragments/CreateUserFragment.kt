package com.example.databaseexample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.databaseexample.R
import com.example.databaseexample.model.UserModel
import com.example.databaseexample.services.UserService
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [CreateUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateUserFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() =
            CreateUserFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_create_user,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val etName = view.findViewById<EditText>(R.id.etName)
        val etApellido = view.findViewById<EditText>(R.id.etLastname)
        val etAge = view.findViewById<EditText>(R.id.etEdad)
        val cbActivo = view.findViewById<CheckBox>(R.id.cbActive)

        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {

            val userService = UserService.instance
            val edad = Integer.parseInt(etAge.text.toString())

            //Se crea el usuario con los valores capturados en el formulario
            val user = UserModel(
                Random().nextInt(), //Se genera un id aleatorio
                etName.text.toString(),
                etApellido.text.toString(),
                edad,
                cbActivo.isChecked
            )

            //Se agrega el usuario creado a la lista por medio del servicio
            userService.addUser(user)

            this.requireActivity().supportFragmentManager.popBackStack()

        }

    }

}