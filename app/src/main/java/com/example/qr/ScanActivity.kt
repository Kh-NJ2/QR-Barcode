package com.example.qr

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.qr.databinding.ActivityScanBinding
import com.google.zxing.client.android.Intents
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class ScanActivity : AppCompatActivity() {


    private lateinit var capture: CaptureManager
    private lateinit var binding : ActivityScanBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.generatebtn.text = "Scan"
        binding.imagec.background = ContextCompat.getDrawable(this, R.drawable.bemoface2)


//        initialize Qr Scanner
        capture = CaptureManager(this, binding.zxingBarcodeScanner)
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.setShowMissingCameraPermissionDialog(false)
        capture.decode()






//        checkPermission()

    }

    private fun scanQr() {
        val options = ScanOptions()
        options.setPrompt("")
        options.setCameraId(0)
        options.setBeepEnabled(true)
        options.setTorchEnabled(false)
        options.setTimeout(20000)
//        options.addExtra(Intents.Scan.SCAN_TYPE, Intents.Scan.INVERTED_SCAN)
        options.addExtra(Intents.Scan.SCAN_TYPE, Intents.Scan.MIXED_SCAN )
        options.setCaptureActivity(CaptureAct::class.java)

        barcodeLauncher.launch(options)

    }

    val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            binding.imagec.background = ContextCompat.getDrawable(this, R.drawable.bemosad)
            binding.resulttv.text = ":("

        } else {
            binding.resulttv.text = result.contents
            binding.imagec.background = ContextCompat.getDrawable(this, R.drawable.bemoface2)

            binding.resulttv.setOnClickListener {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label", binding.resulttv.text)
                clipboard.setPrimaryClip(clip)
                // Notify the user that the text has been copied
                Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        capture.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return binding.zxingBarcodeScanner.onKeyDown(keyCode, event) || super.onKeyDown(
            keyCode,
            event
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun back(view: View) {
        val intent = Intent(this, mainActivity2::class.java)
        overridePendingTransition(R.anim.fade2, R.anim.fade)
        startActivity(intent)
    }

    fun scanAndStop(view: View) {
        binding.imagec.setImageResource(android.R.color.transparent)
        scanQr()
    }


//    private fun checkPermission(){
//        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
//        if (permission != PackageManager.PERMISSION_GRANTED){
//            requestPermission()
//        }
//    }
//
//    private fun requestPermission(){
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), camRequestCode)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when(requestCode){
//            camRequestCode -> {
//                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED ){
//                    Toast.makeText(this,"Please Allow CAMERA ACCESS From Settings", Toast.LENGTH_LONG) .show()
//                } else {
//                    //success
//                }
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }




}


