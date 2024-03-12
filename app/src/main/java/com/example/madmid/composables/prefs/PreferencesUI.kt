package com.example.madmid.composables.prefs

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.madmid.activities.ui.theme.MadMidTheme

@Composable
fun PreferencesUI(context: Context = LocalContext.current) {

    val sharedPrefManager = remember {
        SharedPreferenceManager(context)
    }


    MadMidTheme {
        PreferencesForm(
            handleOnSave = { key, value -> sharedPrefManager.saveData(key, value) },
            handleOnGet = { key -> sharedPrefManager.getData(key, "") })
    }
}