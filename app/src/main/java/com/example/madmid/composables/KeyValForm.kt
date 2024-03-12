// Used to draw form for setting a key and value as well as
// getting a value with from a key

package com.example.madmid.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
        Text(
            "Save Data",
            fontSize = 30.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(vertical = 10.dp)
        );
        TextField(value = sKey, onValueChange = { sKey = it }, placeholder = { Text("Type Key") })
        TextField(
            value = sValue,
            onValueChange = { sValue = it },
            placeholder = { Text("Type Value") })
        Button(
            onClick = { handleOnSave(sKey, sValue); sKey = ""; sValue = "" },
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text("Save");
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Get Data",
            fontSize = 30.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(vertical = 10.dp)
        );
        TextField(value = gKey, onValueChange = { gKey = it }, placeholder = { Text("Type Key") })
        Button(onClick = {
            gValue = handleOnGet(gKey);
            Log.i("kilo", "$gKey: $gValue");
            gKey = ""
        }, modifier = Modifier.padding(vertical = 10.dp)) {
            Text("Get")
        }
        Text("Value: $gValue", fontSize = 25.sp, modifier = Modifier.padding(vertical = 10.dp));
    }
}