package org.certificatic.webserviceexample.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.airbnb.lottie.LottieAnimationView
import org.certificatic.webserviceexample.R
import org.certificatic.webserviceexample.dto.UsuarioDTO

import org.certificatic.webserviceexample.model.UserModel
import org.certificatic.webserviceexample.services.UserService
import org.certificatic.webserviceexample.services.UserServiceWS
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

        val lavLoading = view.findViewById<LottieAnimationView>(R.id.lavLoading)
        val etName = view.findViewById<EditText>(R.id.etName)
        val etApellido = view.findViewById<EditText>(R.id.etLastname)
        val etAge = view.findViewById<EditText>(R.id.etEdad)
        val cbActivo = view.findViewById<CheckBox>(R.id.cbActive)

        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {

            //Se pone un "loading"
            lavLoading.visibility = View.VISIBLE

            //val userService = UserService.instance
            val userService = UserServiceWS.instance

            val edad = Integer.parseInt(etAge.text.toString())

            //Se crea el usuario con los valores capturados en el formulario
//            val user = UserModel(
//                Random().nextInt(), //Se genera un id aleatorio
//                etName.text.toString(),
//                etApellido.text.toString(),
//                edad,
//                cbActivo.isChecked
//            )

            val user = UsuarioDTO(
                nombre = etName.text.toString(),
                apellido = etApellido.text.toString(),
                edad = edad,
                activo = cbActivo.isChecked
            )

            //Se agrega el usuario creado a la lista por medio del servicio
            //userService.addUser(user)
            userService.createUser(user, { nuevoId ->

                showAlert("Usuario exitosamente creado: $nuevoId")

                Log.d("MPS", "El nuevo usuario con id: $nuevoId")

                lavLoading.visibility = View.INVISIBLE

            }, { mensajeError ->

                showAlert("Ocurrió un error!! $mensajeError")

                Log.d("MPS", "Ocurrió un error al guardar al usuario: $mensajeError")

                lavLoading.visibility = View.INVISIBLE

            })

        }

    }

    fun showAlert(mensaje: String) {

        val builder = AlertDialog.Builder(
            this.requireActivity()
        )

        builder.setMessage(mensaje)
            .setTitle("Atención!")
            .setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, id ->

                    dialog.cancel()

                    this.requireActivity().supportFragmentManager.popBackStack()

                })

        builder.create().show()

    }

}