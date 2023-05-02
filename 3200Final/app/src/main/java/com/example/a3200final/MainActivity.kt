package com.example.a3200final

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var cameraOpenId: Button
    lateinit var topImage: ImageView
    lateinit var bottomImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // By ID we can get each component which id is assigned in XML file get Buttons and imageview.
        cameraOpenId = findViewById(R.id.camera_button)
        topImage = findViewById(R.id.top_image)
        bottomImage = findViewById(R.id.bottom_image)

        topImage.setDrawingCacheEnabled(true)
        bottomImage.setDrawingCacheEnabled(true)

        // Camera_open button is for open the camera and add the setOnClickListener in this button
        cameraOpenId.setOnClickListener(View.OnClickListener { v: View? ->
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(cameraIntent, pic_id)
        })
    }

    // This method will help to retrieve the image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            // Set the image in imageview for display
            bottomImage.setImageBitmap(topImage.drawingCache)
            topImage.setImageBitmap(photo as Bitmap)
        }
    }

    companion object {
        // Define the pic id
        private const val pic_id = 123
    }
}