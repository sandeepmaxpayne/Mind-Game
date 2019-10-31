package com.sandeep.mindgame.memo.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.sandeep.mindgame.R
import com.sandeep.mindgame.memo.Router
import com.sandeep.mindgame.memo.datalayer.GameSizeEnum
import com.sandeep.mindgame.memo.datalayer.GenImageData
import com.sandeep.mindgame.memo.datalayer.localdb.SharedPreference
import com.sandeep.mindgame.model.PlayListAdapter
import kotlinx.android.synthetic.main.activity_play.*
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


@Suppress("JavaCollectionsStaticMethodOnImmutableList")
class PlayActivity : BaseActivity(), View.OnClickListener {

    lateinit var sharedPreference: SharedPreference
    var userName = ""
    var gameType = 0
    var gameSize = 0
    var stopTime: Long = 0
    var andTime = ""
    var timer = Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        sharedPreference = SharedPreference(this)
        btnBack.setOnClickListener(this)
        btnPause.setOnClickListener(this)
        btnPlay.setOnClickListener(this)
        if (sharedPreference.getValueInt("GameType")!= 0
            && sharedPreference.getValueInt("GameSize") != 0
            && !sharedPreference.getValueString("UserName").isNullOrEmpty()){
            gameType = sharedPreference.getValueInt("GameType")!!
            userName = sharedPreference.getValueString("UserName").toString()
            when (sharedPreference.getValueInt("GameSize")){
                1 -> {
                    gameSize = GameSizeEnum.SMALL.value
                    gridView.numColumns = 2
                    andTime = "00:30"
                }
                2 -> {
                    gameSize = GameSizeEnum.BIG.value
                    gridView.numColumns = 4
                    andTime = "1:00"
                }
            }
            userName = sharedPreference.getValueString("UserName").toString()
        }
        val numberList: ArrayList<Int> = ArrayList()
        var i = 0
        var control: Boolean
        while (i < gameSize){
            control = true
            val randomNum = Random.nextInt(0, gameSize)
            for (l in 0 until numberList.size){
                if (numberList[l] == randomNum){
                    control = false
                    break
                }
            }
            if (control){
                numberList.add(randomNum)
                i += 1
            }
        }
        val imageList = GenImageData.getImage(gameType, gameSize, getActivityContext()!!)
        Collections.shuffle(imageList)
        with(gridView){
            adapter = PlayListAdapter(imageList, context, numberList, getActivity()!!, gameType, userName)
        }
        txtTime.base = SystemClock.elapsedRealtime()
        txtTime.start()
        txtTime.setOnChronometerTickListener {
            val time = it.text
            Log.d("time", "time: $time")
            if (andTime == time.toString()){
                Log.d("time", "stopTime: $time")
                txtTime.stop()
                addAlertDialog()

            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0){
            btnBack ->{
                stopTime = txtTime.base - SystemClock.elapsedRealtime()
                txtTime.stop()
                addAlertDialog()
            }
            btnPause ->{
                stopTime = txtTime.base - SystemClock.elapsedRealtime()
                txtTime.stop()
                btnPlay.visibility = View.VISIBLE
                btnPause.visibility = View.GONE
            }
            btnPlay -> {
                txtTime.base = SystemClock.elapsedRealtime() + stopTime
                txtTime.start()
                btnPlay.visibility = View.GONE
                btnPause.visibility = View.VISIBLE
            }
        }
    }
    @SuppressLint("PrivateResource", "SetTextI18n")
    private fun addDialog(){
        val dialogControl = AlertDialog.Builder(this)
        val mView = this.layoutInflater.inflate(R.layout.custom_dialog, null)
        val okButton = mView.findViewById<Button>(R.id.dialog_submit)
        val txtinformation = mView.findViewById<TextView>(R.id.txtInfo)
        val txtScoreTitle1 = mView.findViewById<TextView>(R.id.txtScoreTitle)
        val txtScores = mView.findViewById<TextView>(R.id.txtScore)
        txtScoreTitle1.text = "Score Title"
        txtinformation.gravity = Gravity.CENTER_HORIZONTAL
        txtScores.gravity = Gravity.CENTER_HORIZONTAL
        txtScores.text = ""
        dialogControl.setOnDismissListener {
            timer = Timer()
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    getActivity()!!.runOnUiThread {
                        val router = Router()
                        router.switchPage(this@PlayActivity, HomeActivity())
                        this@PlayActivity.finishAffinity()
                        timer.cancel()
                    }
                }
            }, 0, 300000)
        }
        dialogControl.setView(mView)
        val alertDialog = dialogControl.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCancelable(false)
        okButton.setOnClickListener {
            alertDialog.dismiss()
        }
    }



    @SuppressLint("InflateParams")
    private fun addAlertDialog() {
        val dialogControl = AlertDialog.Builder(this)
        val mView = this.layoutInflater.inflate(R.layout.custom_alert, null)
        val yepButton = mView.findViewById<Button>(R.id.btnYes)
        val noButton = mView.findViewById<Button>(R.id.btnNo)
        dialogControl.setOnDismissListener {

        }
        dialogControl.setView(mView)
        val alertDialog = dialogControl.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCancelable(false)
        yepButton.setOnClickListener {
            alertDialog.dismiss()
            timer = Timer()
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    getActivity()!!.runOnUiThread(Runnable {
                        val router = Router()
                        router.switchPage(getActivityContext(), HomeActivity())
                        this@PlayActivity.finishAffinity()
                        timer.cancel()
                    })
                }
            }, 0, 300000)
        }
        noButton.setOnClickListener{
            txtTime.base = SystemClock.elapsedRealtime() + stopTime
            txtTime.start()
            alertDialog.dismiss()
        }
    }


}
