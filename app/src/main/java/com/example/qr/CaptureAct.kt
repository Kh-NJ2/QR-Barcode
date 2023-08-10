package com.example.qr

import android.content.Intent
import android.view.View
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class CaptureAct : CaptureActivity() {

    override fun initializeContent(): DecoratedBarcodeView {
        setContentView(R.layout.activity_scan)
        return (findViewById<View>(R.id.zxing_barcode_scanner) as DecoratedBarcodeView)
    }

    fun back(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        overridePendingTransition(R.anim.fade2, R.anim.fade)
        startActivity(intent)
    }

    fun scanAndStop(view: View) {
        finish()
    }
}