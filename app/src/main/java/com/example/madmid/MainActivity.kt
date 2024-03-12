package com.example.madmid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.madmid.activities.ActivitySelfieCompose
import com.example.madmid.activities.ActivitySelfieXML
import com.example.madmid.composables.Navigation
import com.example.madmid.ui.theme.MadMidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation();
//            MadMidTheme {
//                MainScreenUI();
//            }
        }
    }
}

// Demonstrates use of explicit intent to navigate between two activities
@Composable
fun MainScreenUI(context: Context = LocalContext.current, navController: NavController? = null) {
    // Define Intents for Activities
    val activitySelfieXMLIntent: Intent = Intent(context, ActivitySelfieXML::class.java)
    activitySelfieXMLIntent.putExtra("username", "arslan");

    val activitySelfieComposeIntent: Intent = Intent(context, ActivitySelfieCompose::class.java);

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if(navController != null) ComposeBasedNavigationRoutes(navController = navController);
        // Intent Based Navigation below ⬇️
        Button(
            onClick = { context.startActivity(activitySelfieXMLIntent) },
        ) {
            Text(text = "Take Selfie! Old Fashion Way", fontSize = 20.sp);
        }
        Button(onClick = { context.startActivity(activitySelfieComposeIntent) }) {
            Text(text = "Take Selfie! Compose Style", fontSize = 20.sp);
        }
    }

}

@Composable
fun ComposeBasedNavigationRoutes(navController: NavController) {
    Button(onClick = { navController.navigate("chat") }) {
        Text(text = "View Chat", fontSize = 20.sp);
    }
    Button(onClick = { navController.navigate("water_counter/10") }) {
        Text(text = "Water Counter", fontSize = 20.sp);
    }
    Button(onClick = { navController.navigate("preferences") }) {
        Text(text = "Shared Preferences", fontSize = 20.sp);
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MadMidTheme {

    }
}