package org.certificatic.pushnotificationexample.firebase

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.certificatic.pushnotificationexample.MainActivity
import org.certificatic.pushnotificationexample.R

class CustomFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.i("MPS", "onNewToken: $token")

        //TODO Enviar a un WS
        //TODO Guardarlo para identificar el dispositivo/usuario
        //TODO Guardarlo en BD Local
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification?.title
        val body = message.notification?.body

        Log.i("MPS", "TITULO: $title")
        Log.i("MPS", "BODY: $body")

        //TODO invocar un WS de sincronización
        //TODO Guardar en BD  la información enviada por la notificación

//        val channelId = getString(R.string.default_notification_channel_id)
//        var notificationBuilder = NotificationCompat.Builder(this, channelId)
//            .setContentTitle(title)
//            .setContentText(body)
//
//        notificationBuilder.build()
//
//        val intent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 999,intent)

    }

}