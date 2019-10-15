package com.sandeep.mindgame.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sandeep.mindgame.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("Home","this is home")
    }
}
