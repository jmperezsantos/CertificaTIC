package org.certificatic.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.certificatic.viewbinding.fragment.ViewBindingFragment

class MainActivityFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragment)

        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, ViewBindingFragment.newInstance())
        transaction.commit()

    }
}