package com.example.qr

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.qr.databinding.ActivityBarcodeBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder


class BarcodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarcodeBinding
    private var codeType : Int = -1
    private var MaxLength : Int = -1
    private var formats = arrayOf(BarcodeFormat.CODE_128, BarcodeFormat.CODE_39, BarcodeFormat.CODE_93)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        codeType = intent.getIntExtra("BarcodeType", 0)
        MaxLength = intent.getIntExtra("MaxLength", 0)

        val filters = arrayOfNulls<InputFilter>(1)
        filters[0] = LengthFilter(MaxLength)
        binding.et2.setFilters(filters)

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

        val text = binding.et2.text.toString().trim()
        val width = binding.resultIm2.measuredWidth
        val height = binding.resultIm2.measuredHeight
        val writer = MultiFormatWriter()

        if (!TextUtils.isEmpty(text)){
            val matrix : BitMatrix = writer.encode(text, formats[codeType], width, height)
            val encoder = BarcodeEncoder()
            val bitmap : Bitmap = encoder.createBitmap(matrix)
            binding.resultIm2.setImageBitmap(bitmap)
        }else{
            binding.et2.hint = ":("
            binding.resultIm2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.bemosad))
        }



    }
}