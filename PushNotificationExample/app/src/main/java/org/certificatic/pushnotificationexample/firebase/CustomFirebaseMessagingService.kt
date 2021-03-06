package org.certificatic.pushnotificationexample.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.certificatic.pushnotificationexample.MainActivity
import org.certificatic.pushnotificationexample.R
import java.util.*
import kotlin.math.log

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

        if (message.data != null) {
            Log.i("MPS", "idAlgo: ${message.data["idAlgo"]}")
            Log.i("MPS", "extra1: ${message.data["extra1"]}")
            Log.i("MPS", "extra2: ${message.data["extra2"]}")
        }

        Log.i("MPS", "TITULO: $title")
        Log.i("MPS", "BODY: $body")

        //TODO invocar un WS de sincronización
        //TODO Guardar en BD  la información enviada por la notificación

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.notification)
            .setColor(getColor(R.color.notification_color))
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(pendingIntent)

        val notificationId = Random().nextInt()

        val notificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = notificationBuilder.build()
        notificationManager.notify(notificationId, notification)

    }

}