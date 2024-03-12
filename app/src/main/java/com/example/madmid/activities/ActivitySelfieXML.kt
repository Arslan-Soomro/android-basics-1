// Illustrates get additional info from explicit intent
// Illustrates use of explicit intent by opening camera app in old fashion

package com.example.madmid.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.madmid.ui.theme.MadMidTheme

class ActivitySelfieXML : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.i("kilo", "Permission Granted")
        } else {
            Log.i("kilo", "Permission Denied")
        }
    }

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            // Do something with the captured image URI
            Log.i("kilo: ", "Image URI: $imageUri");
        }
    }


    fun requestCameraAccess() {
        val permissionCheckResult = this.checkSelfPermission(Manifest.permission.CAMERA);
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            val camIntent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(camIntent);
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get username from intent, supplied by the previous activity
        val intent: Intent = getIntent();
        val username: String = intent.getStringExtra("username") ?: "Anonymus";

        setContent {
            MadMidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SelfieGreeting(username)
                        Button(onClick = { requestCameraAccess() }) {
                            Text("Open Camera");
                        }
                    }
                }
            }
        }
    }
}