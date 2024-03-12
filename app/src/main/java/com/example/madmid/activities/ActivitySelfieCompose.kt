// Illustrates opening a camera using compose and intents

package com.example.madmid.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.madmid.activities.ui.theme.MadMidTheme

class ActivitySelfieCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MadMidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OpenCameraView();
                }
            }
        }
    }
}

@Composable
fun OpenCameraView(context: Context = LocalContext.current) {
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("kilo", "Camera Permission Granted")
        }else {
            Log.i("kilo", "Camera Permission Denied")
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data = result.data;
            Log.i("kilo", "Image Captured: ${data.toString()}");
        }else {
            Log.i("kilo", "Image Capture Failed");
        }
    }

    fun requestCameraAccess() {
        val permissionCheckResult = context.checkSelfPermission(Manifest.permission.CAMERA);
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            val camIntent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(camIntent);
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SelfieGreeting("Android")
        Button(onClick = { requestCameraAccess() }) {
            Text("Open Camera");
        }
    }
}