package com.basgroup.turboservicebarcode.ui.scanner

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.TransformExperimental
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.basgroup.turboservicebarcode.R

import com.basgroup.turboservicebarcode.databinding.ActivityScannerBinding
import com.basgroup.turboservicebarcode.ui.foundItems.FoundItemsActivity
import com.basgroup.turboservicebarcode.ui.scanMain.ScanMainActivity

import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors

private const val CAMERA_PERMISSION_REQUEST_CODE = 1
@TransformExperimental @ExperimentalGetImage
class ScannerActivity : AppCompatActivity() {
    var right = 0
    var bottom = 0
    var camTop=0
    var camBot=0

    var width = 0
    var height = 0

    private val vm by viewModel<ScannerViewModel>()
    private val binding : ActivityScannerBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_scanner)
    }
    private var isScanned:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        listeners()
        camTop=(480-binding.cameraView.height)/2
        camBot=(480-binding.cameraView.height)/2

        width=binding.cameraView.width
        height=binding.cameraView.height


        if (hasCameraPermission()) bindCameraUseCases()
        else requestPermission()


    }
    fun listeners(){
        binding.btnCancel.setOnClickListener {
            ScanMainActivity.start(this)
        }
        binding.srcViewBarcode.setIconifiedByDefault(false)
        binding.srcViewBarcode.queryHint=getString(R.string.search_barcode_hint)
        binding.srcViewBarcode.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByBarcode(binding.srcViewBarcode.query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }




    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            bindCameraUseCases()
        } else {
            Toast.makeText(this,
                "Camera permission required",
                Toast.LENGTH_LONG
            ).show()
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    private fun bindCameraUseCases() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            //val viewPort =  ViewPort.Builder(Rational(600, 200), rotation).build()
            val previewUseCase = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.cameraView.surfaceProvider)

                }

            val options = BarcodeScannerOptions.Builder().setBarcodeFormats(
                Barcode.FORMAT_CODE_128,
                Barcode.FORMAT_CODE_39,
                Barcode.FORMAT_CODE_93,
                Barcode.FORMAT_EAN_8,
                Barcode.FORMAT_EAN_13,
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_UPC_A,
                Barcode.FORMAT_UPC_E,
                Barcode.FORMAT_PDF417
            ).build()

            val scanner = BarcodeScanning.getClient(options)


            val analysisUseCase = ImageAnalysis.Builder()
                .build()

            analysisUseCase.setAnalyzer(
                Executors.newSingleThreadExecutor()
            ) { imageProxy ->
                //Log.e("cam","${imageProxy.width} ${imageProxy.height}")//640 480
                processImageProxy(scanner, imageProxy)
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    previewUseCase,
                    analysisUseCase)
            } catch (illegalStateException: IllegalStateException) {
                Log.e("bindCamera", illegalStateException.message.orEmpty())
            } catch (illegalArgumentException: IllegalArgumentException) {
                Log.e("bindCamera", illegalArgumentException.message.orEmpty())
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun processImageProxy(
        barcodeScanner: BarcodeScanner,
        imageProxy: ImageProxy
    ) {
        barcodeScanner.optionalFeatures
        imageProxy.image?.let { image ->
            //image.cropRect.set()
            //Log.e("imageheight",image.height.toString())
            val inputImage =
                InputImage.fromMediaImage(
                    image,
                    imageProxy.imageInfo.rotationDegrees
                )


            barcodeScanner.process(inputImage)
                .addOnSuccessListener { barcodeList ->
                    //var barcode:Barcode
                    //for (i in barcodeList)
                    //    if(i.boundingBox== Rect(0,0,right, bottom))
                    val barcode = barcodeList.getOrNull(0)
                    //Log.e("boundingbox",barcode?.boundingBox.toString())
                    if (barcode!=null)
                        if (barcode?.boundingBox?.top!! > 150 && barcode?.boundingBox?.bottom!!<480)
                            barcode?.rawValue?.let { value ->
                                if (!isScanned){
                                    //Log.e("BB","$camTop ${barcode?.boundingBox?.top!!}")
                                    isScanned=true
                                    searchByBarcode(value)
                                }
                                    //getString(R.string.barcode_value, value)
                            }
                }
                .addOnFailureListener {
                    Log.e("bindCamera", it.message.orEmpty())
                }.addOnCompleteListener {
                    imageProxy.image?.close()
                    imageProxy.close()
                }
        }
    }

    fun searchByBarcode(barcode:String){
        vm.findByBarcode(barcode)
        FoundItemsActivity.start(this)
    }


    companion object{
        fun start(context: Context){
            val intent = Intent(context, ScannerActivity::class.java)
            context.startActivity(intent)
        }
    }
}

