package com.example.qr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.qr.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {


    private lateinit var binding : ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun btn(view: View) {

        if(view == binding.generatebtn){
            binding.generatebtn.isPressed = true
            val intent = Intent(this, GenerateActivity::class.java)
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
            startActivity(intent)

        }else if (view == binding.scanbtn){
            binding.scanbtn.isPressed = true
            val intent = Intent(this, ScanActivity::class.java)
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
            startActivity(intent)

        }else if(view == binding.barCodebtn){
            val intent = Intent(this, TypeActivity::class.java)
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
            startActivity(intent)
        }
    }
}