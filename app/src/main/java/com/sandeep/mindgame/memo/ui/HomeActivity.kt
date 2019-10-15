package com.sandeep.mindgame.memo.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sandeep.mindgame.R
import com.sandeep.mindgame.memo.datalayer.SharedPreference
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : View.OnClickListener, BaseActivity() {


    var check = true
    lateinit var sharedPreference: SharedPreference
    var score: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnPlay.setOnClickListener(this)
        sharedPreference = SharedPreference(this)
        if (!sharedPreference.getValueString("UserName").isNullOrEmpty()){
            txtUser.text = sharedPreference.getValueString("UserName")
            txtScore.text = sharedPreference.getValueString("Score").toString()

        }
    }

    override fun onClick(p0: View?) {
        //To change body of created functions use File | Settings | File Templates.
        when (p0){
            btnPlay -> {
                check = true
                if (spnrGameType.selectedItemId.toInt() == 0){
                    showAlert("No Game Selected", this@HomeActivity, R.color.colorRed)
                    check = false
                }
                if (spnrGameSize.selectedItemId.toInt() == 0){
                    showAlert("No Item Selected", this@HomeActivity, R.color.colorRed)
                    check = false
                }
                if (txtUsername.text.isNullOrEmpty()){
                    showAlert("Enter UserName", this@HomeActivity, R.color.colorRed)
                    check = false
                }
                if (check){
                    val intent = Intent(this@HomeActivity, PlayActivity::class.java)
                    sharedPreference.save("GameType", spnrGameType.selectedItemId.toInt())
                    sharedPreference.save("GameSize", spnrGameSize.selectedItemId.toInt())
                    sharedPreference.save("UserName", txtUsername.textAlignment.toString())
                    startActivity(intent)
                }

            }
            btnScoreBoard -> {
                val intent = Intent(this@HomeActivity, ScoreBoard::class.java)
                startActivity(intent)
            }
        }
    }
}
