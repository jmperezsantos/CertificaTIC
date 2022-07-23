package org.certificatic.cameraexample

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log

import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var ivImage: ImageView
    private lateinit var btnTakePhoto: Button

    private val resultReceiver =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            setImageToImageView(bitmap)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.ivImage = findViewById(R.id.ivImage)
        this.btnTakePhoto = findViewById(R.id.btnTakePhoto)

        this.btnTakePhoto.setOnClickListener {
            this.resultReceiver.launch(null)
        }

    }


    private fun setImageToImageView(bitmap: Bitmap?) {
//        if (bitmap != null) {
//            this.ivImage.setImageBitmap(bitmap)
//        }

        //(obj != null)
        //also -> ejecutar métodos de una instancia
        //appply -> inicializar props instancia despues de constructor
        //let -> validación de referencias

        bitmap?.let { imagen ->
            ivImage.setImageBitmap(imagen)

            val byteArrayOutput = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutput)

            val byteArray = byteArrayOutput.toByteArray()

            val stringBase64 = Base64.encodeToString(
                byteArray,
                Base64.DEFAULT
            )

            Log.i("MPS", "Base64:")
            Log.i("MPS", stringBase64)

            //TODO Subir a WS
            //TODO Guardar en BD

        }

    }

}