package com.example.qr

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class BarcodeActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var generatetext: EditText
    private var codeType: Int = -1
    private var formats = arrayOf(BarcodeFormat.CODE_128, BarcodeFormat.CODE_39, BarcodeFormat.CODE_93)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        imageView = findViewById(R.id.resultIm2)
        generatetext = findViewById(R.id.et2)
        codeType = intent.getIntExtra("BarcodeType", 0)
    }

    fun back(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        overridePendingTransition(R.anim.fade2, R.anim.fade)
        startActivity(intent)
    }

    fun generate(view: View) {
        generateQR()
    }

    private fun generateQR() {

        val text = generatetext.text.toString().trim()
        val width = imageView.measuredWidth
        val height = imageView.measuredHeight
        val writer = MultiFormatWriter()

        if (!TextUtils.isEmpty(text)){
            val matrix : BitMatrix = writer.encode(text, formats[codeType], width, height)
            val encoder = BarcodeEncoder()
            val bitmap : Bitmap = encoder.createBitmap(matrix)
            imageView.setImageBitmap(bitmap)
        }else{
            generatetext.hint = ":("
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.bemosad))
        }



    }
}