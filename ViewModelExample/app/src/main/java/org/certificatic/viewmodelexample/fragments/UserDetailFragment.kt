package org.certificatic.viewmodelexample.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import org.certificatic.viewmodelexample.R
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import org.certificatic.viewmodelexample.dto.UsuarioDTO
import org.certificatic.viewmodelexample.services.UserServiceWS
import org.certificatic.viewmodelexample.viewmodel.UserDetailViewModel
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    lateinit var viewModel: UserDetailViewModel

    //Para acomodar miembros (métodos/propiedades) estáticos
    companion object {

        //TODO Tiene que recibir un UsuarioDTO
        @JvmStatic
        fun newInstance(user: UsuarioDTO): UserDetailFragment {

            return UserDetailFragment().apply {
                arguments = Bundle().apply {

                    putSerializable("USER_ARG", user)

                }
            }
        }
    }

    //Propiedades del Fragment
    private lateinit var user: UsuarioDTO

    //Configuración "Lógica"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.arguments?.let { it ->

            this.user = it.getSerializable("USER_ARG") as UsuarioDTO
            Log.d("MPS", "Llegó: $user")

        }

        this.viewModel = ViewModelProvider(this)
            .get(UserDetailViewModel::class.java)

        this.viewModel.isUserUpdated().observe(this) { updated ->
            if (updated) {
                showDialog("Usuario actualizado exitosamente!!")
            } else {
                showDialog("El usuario no se actualizó!!")
            }
        }

        this.viewModel.isUserDeleted().observe(this) { deleted ->
            if (deleted) {
                showDialog("Usuario eliminado exitosamente!!")
            } else {
                showDialog("El usuario no se eliminó!!")
            }
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
        val lavLoading = view.findViewById<View>(R.id.lavLoading)

        btnDelete.setOnClickListener {

            //Se borra al usuario de la lista, por medio del servicio
            // UserService.instance.deleteUser(this.user.uid)

            confirmAlert(this.user)

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

            lavLoading.visibility = View.VISIBLE

            this.user.nombre = tvName.text.toString()
            this.user.apellido = tvLastname.text.toString()
            this.user.edad = Integer.parseInt(tvAge.text.toString())
            this.user.activo = sActivo.isChecked

            this.viewModel.updateUser(this.user)

            //UserService.instance.updateUser(this.user)
//            this.userService.updateUser(
//                this.user,
//                { updatedUser ->
//                    showDialog("Usuario actualizado exitosamente")
//
//                    lavLoading.visibility = View.INVISIBLE
//                },
//                { errorMessage ->
//                    showDialog(errorMessage)
//
//                    lavLoading.visibility = View.INVISIBLE
//                }
//            )
        }

        tvName.setText(this.user.nombre)
        tvLastname.setText(this.user.apellido)
        tvAge.setText("${this.user.edad}")
        sActivo.isChecked = this.user.activo

    }

    private fun showDialog(message: String) {

        val builder = AlertDialog.Builder(this.requireContext())

        builder.setMessage(message)
            .setTitle("Atención!")
            .setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, id ->

                    dialog.cancel()

                    this.requireActivity().supportFragmentManager.popBackStack()

                })

        builder.create().show()

    }

    private fun confirmAlert(userToDelete: UsuarioDTO) {

        val builder = AlertDialog.Builder(this.requireContext())

        val message = "Estás seguro de eliminar a ${userToDelete.nombre} ${userToDelete.apellido}"

        builder.setMessage(message)
            .setTitle("Atención!")
            .setPositiveButton("Sí",
                DialogInterface.OnClickListener { dialog, id ->

                    this.viewModel.deleteUser(userToDelete)
//                    this.userService.deleteUser(userToDelete.id!!,
//                        {
//                            showDialog("${userToDelete.nombre} ${userToDelete.apellido} eliminado exitosamente")
//                        },
//                        { errorMessage ->
//                            showDialog(errorMessage)
//                        })

                })
            .setNegativeButton("No") { dialog, id ->

                dialog.cancel()

            }

        builder.create().show()
    }

}