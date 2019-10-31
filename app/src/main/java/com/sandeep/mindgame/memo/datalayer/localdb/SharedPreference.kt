package com.sandeep.mindgame.memo.datalayer.localdb

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPreference (val context: Context){
    private val PREF = "Mind"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    @SuppressLint("CommitPrefEdits")
    fun save(Key_Name: String, text: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(Key_Name, text)
        editor.apply()
    }
    fun save(Key_Name: String, value: Int){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(Key_Name, value)
        editor.apply()
    }
    fun save(Key_Name: String, status: Boolean){
        val editor:SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(Key_Name, status)
        editor.apply()
    }

    fun getValueString(Key_Name: String): String? = sharedPref.getString(Key_Name, null)

    fun getValueInt(Key_Name: String): Int? = sharedPref.getInt(Key_Name, 0)

    fun getValueBoolean(Key_Name: String, defaultValue: Boolean): Boolean = sharedPref.getBoolean(Key_Name, defaultValue)

    fun clearSharedPreference(){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
    fun removeValue(Key_Name: String){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(Key_Name)
        editor.apply()
    }
}
