package org.certificatic.dependencyinjectionexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CustomApplication : Application() {

    //Se ejecuta cuando la Aplicación es creada
    override fun onCreate() {
        super.onCreate()

        //inicializar Firebase Analíticas
        //inicializar Firebase Push Notification
    }

}