package com.example.madmid.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.madmid.activities.ui.theme.MadMidTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PreferencesUI(context: Context = LocalContext.current) {


}

@Composable
fun SharedPreferencesUI(context: Context = LocalContext.current) {
    val sharedPrefManager = remember {
        SharedPreferenceManager(context)
    }

    MadMidTheme {
        PreferencesForm(
            handleOnSave = { key, value -> sharedPrefManager.saveData(key, value) },
            handleOnGet = { key -> sharedPrefManager.getData(key, "") })
    }
}

@Composable
fun DataStorePreferencesUI(context: Context = LocalContext.current) {
    val dataStorePrefManager = remember {
        DataStorePreferencesManager(context)
    }

    MadMidTheme {
        PreferencesForm(
            handleOnSave = { key, value ->
               // dataStorePrefManager.saveData(key, value)
                           },
            handleOnGet = { key -> key
//                CoroutineScope(Dispatchers.IO).launch {
//                    dataStorePrefManager.getData(key)
//                }
            })
    }

}