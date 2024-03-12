package com.example.madmid.activities

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun SelfieGreeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name! lets take a selfie",
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 30.sp
    )
}