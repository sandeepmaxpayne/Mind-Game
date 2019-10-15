package com.sandeep.mindgame.memo.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sandeep.mindgame.R
import com.sandeep.mindgame.memo.datalayer.SharedPreference
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {


    var check = true
    lateinit var sharedPreference: SharedPreference
    var score: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnPlay.setOnClickListener(this)
        sharedPreference = SharedPreference(this)
        if (!sharedPreference.getValueBoolean("UserName").isN)
    }

    override fun onClick(p0: View?) {
        //To change body of created functions use File | Settings | File Templates.
    }
}
