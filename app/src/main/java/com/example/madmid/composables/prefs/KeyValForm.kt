package com.example.madmid.composables.prefs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PreferencesForm(
    handleOnSave: (key: String, value: String) -> Unit,
    handleOnGet: (key: String) -> String
) {
    var sKey by remember {
        mutableStateOf("")
    }

    var sValue by remember {
        mutableStateOf("")
    }

    var gKey by remember {
        mutableStateOf("")
    }

    var gValue by remember {
        mutableStateOf("")
    }

    // TODO Add Styling and Test
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Text("Setter");
        Text("Key")
        TextField(value = sKey, onValueChange = { sKey = it })
        Text("Value")
        TextField(value = sValue, onValueChange = { sValue = it })
        Button(onClick = { handleOnSave(sKey, sValue) }) {

        }
        Text("Getter");
        Text("Key")
        TextField(value = gKey, onValueChange = { gKey = it })
        Button(onClick = { gValue = handleOnGet(sKey) }) {

        }
        Text("Value: $gValue");
    }
}