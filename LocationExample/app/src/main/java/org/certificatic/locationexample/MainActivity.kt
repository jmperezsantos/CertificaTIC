package org.certificatic.locationexample

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    lateinit var tvLatitud: TextView
    lateinit var tvLongitud: TextView
    lateinit var latLongContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvLatitud = findViewById(R.id.tvLatitud)
        this.tvLongitud = findViewById(R.id.tvLongitud)
        this.latLongContainer = findViewById(R.id.latLongContainer)

        startLocationCollection()

    }

    private fun startLocationCollection() {

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            ||
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            val permissions = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )

            requestPermissions(permissions, 999)

        } else {

            val locationManager =
                this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                10f,
                object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        Log.i("MPS", "Latitud: ${location.latitude}")
                        Log.i("MPS", "Longitud: ${location.longitude}")

                        //Se muestra en la UI
                        tvLatitud.text = "Latitud: ${location.latitude}"
                        tvLongitud.text = "Longitud: ${location.longitude}"

                        //Con resolución de alcance al contexto de Activity
                        val tvLat = TextView(this@MainActivity)
                        //Con referencia al contexto de la app
                        val tvlon = TextView(applicationContext)

                        tvLat.text = "Latitud: ${location.latitude}"
                        tvlon.text = "Longitud: ${location.longitude}"

                        latLongContainer.addView(tvLat)
                        latLongContainer.addView(tvlon)

                    }

                }
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 999) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("MPS", "Los permisos han sido otorgados :)")

                //Para empezar la colecta
                startLocationCollection()

            } else {
                Log.e("MPS", "Los permisos NO han sido otorgados :(")
            }

        }

    }

}