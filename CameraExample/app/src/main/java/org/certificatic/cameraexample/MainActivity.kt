package org.certificatic.cameraexample

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 888
    }

    private lateinit var ivImage: ImageView
    private lateinit var btnTakePhoto: Button

    private val resultReceiver =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            setImageToImageView(it)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.ivImage = findViewById(R.id.ivImage)
        this.btnTakePhoto = findViewById(R.id.btnTakePhoto)

        this.btnTakePhoto.setOnClickListener { takePhoto() }

    }

    private fun takePhoto() {
        this.resultReceiver.launch(null)
    }

    private fun setImageToImageView(bitmap: Bitmap?) {
        this.ivImage.setImageBitmap(bitmap)
    }

}