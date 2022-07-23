package org.certificatic.pushnotificationexample

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestMessagingPermisions()//TODO Validar con Android Tiramisu (13)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val token = task.result
                Log.i("MPS", "addOnCompleteListener: $token")

                //TODO Enviar a un WS
                //TODO Guardarlo para identificar el dispositivo/usuario
                //TODO Guardarlo en BD Local

            } else {
                Log.e("MPS", "No fue posible obtener un token!")
            }
        }

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val id = getString(R.string.default_notification_channel_id)
            val descriptionText = getString(R.string.channel_description)

            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, name, importance)
            channel.description = descriptionText

            // Register the channel with the system
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun requestMessagingPermisions() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
//            PackageManager.PERMISSION_GRANTED
//        ) {
//
//        } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
//            //TODO Poner un aviso
//        } else {
//            // Directly ask for the permission
//            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
//        }
    }
}