// Illustrates use of SharedPreferenceManager for saving and retrieving data

package com.example.madmid.composables

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager(context: Context){
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value);
        editor.apply();
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue;
    }
}