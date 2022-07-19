package org.certificatic.googlemapsexample

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cargarMapa()

    }

    private fun cargarMapa(){
        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                999
            )

        } else {

            val gmMap = supportFragmentManager.findFragmentById(R.id.gmMap) as SupportMapFragment
            gmMap.getMapAsync { googleMap ->

                this.googleMap = googleMap

                this.googleMap.isMyLocationEnabled = true

                moveCamera()

                addMarks()

            }
        }
    }


    @SuppressLint("MissingPermission")
    private fun moveCamera() {

        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
            LatLng(23.246886,-106.4924897),
            10f
        )
        this.googleMap.moveCamera(cameraUpdate)

//        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//
//        locationManager.requestLocationUpdates(
//            LocationManager.GPS_PROVIDER,
//            5000,
//            10f
//        ) { location ->
//
//            //this.googleMap.moveCamera(cameraUpdate)
//        }

        /*
        1: World
        5: Landmass/continent
        10: City
        15: Streets
        20: Buildings
        */

    }

    private fun addMarks() {

        val gmMarkAcapulco = MarkerOptions()
        gmMarkAcapulco.position(LatLng(16.8356128, -99.9325198))
        gmMarkAcapulco.title("Acapulco")

        val gmMarkZocalo = MarkerOptions()
        gmMarkZocalo.position(LatLng(19.4326068, -99.1353989))
        gmMarkZocalo.title("Zócalo CDMX")
        gmMarkZocalo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))

        val gmMarkMazatlan = MarkerOptions()
        gmMarkMazatlan.position(LatLng(23.2468188, -106.4573823))
        gmMarkMazatlan.title("Sucursal Mazatlán")
        gmMarkMazatlan.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_tienda))

        this.googleMap.addMarker(gmMarkZocalo)
        this.googleMap.addMarker(gmMarkMazatlan)
        this.googleMap.addMarker(gmMarkAcapulco)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 999) {
            cargarMapa()
        }
    }
}