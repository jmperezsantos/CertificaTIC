package org.certificatic.viewbinding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.certificatic.viewbinding.R
import org.certificatic.viewbinding.databinding.FragmentViewBinding

class ViewBindingFragment : Fragment() {

    private var viewBinding: FragmentViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.viewBinding = FragmentViewBinding.inflate(
            inflater,
            container,
            false
        )

        return this.viewBinding!!.root
//        return inflater.inflate(R.layout.fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewBinding!!.cbActivo.isChecked = true
        this.viewBinding!!.btnAcceder.setOnClickListener {
            Toast.makeText(
                this.requireContext(),
                "Botón Presionado",
                Toast.LENGTH_LONG
            ).show()
        }

        this.viewBinding!!.tvTitle.text = "Nuevo Título"
        this.viewBinding!!.footer.tvLabel

    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ViewBindingFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}