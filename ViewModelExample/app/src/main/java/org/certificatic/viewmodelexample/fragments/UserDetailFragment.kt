package org.certificatic.viewmodelexample.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import org.certificatic.viewmodelexample.R
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import org.certificatic.viewmodelexample.databinding.FragmentUserDetailBinding
import org.certificatic.viewmodelexample.dto.UsuarioDTO
import org.certificatic.viewmodelexample.viewmodel.UserDetailViewModel
import java.io.ByteArrayOutputStream


/**
 * A simple [Fragment] subclass.
 * Use the [UserDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class UserDetailFragment() : Fragment() {

    private lateinit var viewModel: UserDetailViewModel
    private lateinit var viewBinding: FragmentUserDetailBinding

    private val resultReceiver =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { imagen ->
            setImageToImageView(imagen)
        }

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        this.viewBinding = FragmentUserDetailBinding.inflate(inflater, container, false)
        this.viewBinding.user = this.user

        return this.viewBinding.root

    }

    //Configuración de UI
    override fun onViewCreated(
        view: View, //Aqui vive la interfaz gráfica.
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        this.viewBinding.ivUser.setOnClickListener {
            this.resultReceiver.launch(null)
        }

        this.user.foto?.let { userImg ->

            val byteArray = Base64.decode(userImg, Base64.DEFAULT)
            val bitMap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            this.viewBinding.ivUser.setImageBitmap(bitMap)

        }

        this.viewBinding.btnDelete.setOnClickListener {

            //Se borra al usuario de la lista, por medio del servicio
            // UserService.instance.deleteUser(this.user.uid)

            confirmAlert(this.user)

        }

        this.viewBinding.btnEditar.setOnClickListener {

            this.viewBinding.tvName.isEnabled = true
            this.viewBinding.tvLastname.isEnabled = true
            this.viewBinding.tvAge.isEnabled = true
            this.viewBinding.swActive.isEnabled = true

            this.viewBinding.tvName.requestFocus(0)
            val inputManager = this.requireContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(this.viewBinding.tvName, InputMethodManager.SHOW_IMPLICIT)

            this.viewBinding.btnEditar.visibility = View.GONE
            this.viewBinding.btnGuardar.visibility = View.VISIBLE

        }

        this.viewBinding.btnGuardar.setOnClickListener {

            this.viewBinding.lavLoading.visibility = View.VISIBLE

//            this.user.nombre = this.viewBinding.tvName.text.toString()
//            this.user.apellido = this.viewBinding.tvLastname.text.toString()
//            this.user.edad = Integer.parseInt(this.viewBinding.tvAge.text.toString())
//            this.user.activo = this.viewBinding.swActive.isChecked

            this.viewModel.updateUser(this.user)

        }

    }

    private fun showDialog(message: String) {

        val builder = AlertDialog.Builder(this.requireContext())

        builder.setMessage(message)
            .setTitle("Atención!")
            .setPositiveButton(
                "Aceptar"
            ) { dialog, id ->

                dialog.cancel()

                this.requireActivity().supportFragmentManager.popBackStack()

            }

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

    private fun setImageToImageView(imagen: Bitmap?) {

        //Kotlin
        imagen?.let { image ->
            this.viewBinding.ivUser.setImageBitmap(image)

            //Se convierte imagen a string Base64
            val byteArrayOutput = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutput)

            val byteArray = byteArrayOutput.toByteArray()

            this.user.foto = Base64.encodeToString(
                byteArray,
                Base64.DEFAULT
            )

        }
    }

}