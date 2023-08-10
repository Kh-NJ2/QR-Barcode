package com.example.qr

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.qr.databinding.ActivityTypeBinding

class TypeActivity : AppCompatActivity() {
    private var codeType : Int = -1
    private var MaxLength : Int = 0
    private lateinit var binding : ActivityTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

    fun btn(view: View) {
        when (view) {
            binding.code1 -> {
                codeType = 0
                MaxLength = 80

            }
            binding.code2 -> {
                codeType = 1
                MaxLength = 40

            }
            binding.code3 -> {
                codeType = 2
                MaxLength = 40

            }
        }

        val intent = Intent(this, BarcodeActivity::class.java)
        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
        intent.putExtra("BarcodeType", codeType)
        intent.putExtra("MaxLength", MaxLength)
        startActivity(intent)
    }
}