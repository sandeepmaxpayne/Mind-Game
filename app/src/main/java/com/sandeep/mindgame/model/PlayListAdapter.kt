package com.sandeep.mindgame.model


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.sandeep.mindgame.R
import com.sandeep.mindgame.memo.Router
import com.sandeep.mindgame.memo.datalayer.localdb.SharedPreference
import com.sandeep.mindgame.memo.datalayer.localdb.db.MindGameDB
import com.sandeep.mindgame.memo.datalayer.localdb.entity.ScoreTableEntity
import com.sandeep.mindgame.memo.datalayer.model.PlayDto
import com.sandeep.mindgame.memo.ui.HomeActivity
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.game_play.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class PlayListAdapter(
    var playlist: List<PlayDto>,
    val context: Context,
    val numList: ArrayList<Int>,
    val activity: Activity,
    val gameType: Int,
    val userName: String): BaseAdapter(){

    val controlList: ArrayList<Int> = ArrayList()
    var getImageView: View? = null
    var temp = 0
    var control: Int = 0
    var score: Int = 0
    var finish = 0
    var timer = Timer()
    lateinit var sharedPreference: SharedPreference

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val container = LayoutInflater.from(context).inflate(R.layout.game_play, p2, false)
        val playCount = playlist[numList[p0]]
        sharedPreference = SharedPreference(context)
        container.imgCard.tag = playCount.urlImg
        if (playCount.type){
            container.lytPlay.visibility = View.INVISIBLE
            container.imgCard.isClickable = false


        }else{
            container.lytPlay.visibility = View.VISIBLE
            container.imgCard.isClickable = true
            container.imgCard.setImageResource(R.drawable.questionsmark)
        }
        activity.txtPlayScore.text= "Player Score: $score"
        container.imgCard.setOnClickListener{
            container.imgCard.setImageResource(container.imgCard.tag.toString().toInt())
            if (getImageView == null){
                getImageView = getView(p0, p1, p2)
                control = numList[p0]
            }
            temp += 1
            container.imgCard.isClickable= false


            if (temp == 2){
                if (getImageView!!.imgCard.tag == container.imgCard.tag){
                    timer= Timer()
                    timer.scheduleAtFixedRate(object : TimerTask(){
                        override fun run() {
                            activity.runOnUiThread {
                                score += 10
                                activity.txtPlayScore.text = "Player Score: $score"
                                notifyDataSetChanged()
                                timer.cancel()
                            }
                        }
                    }, 750, 300000)
                    playlist[control].type = true
                    playlist[numList[p0]].type = true
                    getImageView = null
                    temp = 0
                    finish += 1
                }else{
                    timer = Timer()
                    timer.scheduleAtFixedRate(object : TimerTask(){
                        override fun run() {
                            activity.runOnUiThread {
                                score -= 5
                                activity.txtPlayScore.text = "Player Score: $score"
                                notifyDataSetChanged()
                                timer.cancel()
                            }
                        }
                    }, 1500, 300000)
                    playlist[control].type = false
                    playlist[numList[p0]].type = false
                    getImageView = null
                    temp = 0
                }
            }
            if (finish == numList.size / 2 || score == -50){
                sharedPreference.save("Score", (score+10).toString())
                addNewScore(context, userName, gameType, numList.size, (score+10))
                addDialog()
            }
        }
        return container
    }

    override fun getItem(p0: Int): Any = playlist[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = playlist.size

    @SuppressLint("InflateParams")
    private fun addDialog(){
        val diagControl = AlertDialog.Builder(context)
        val mView = activity.layoutInflater.inflate(R.layout.cutom_dialog, null)
        val okButt = mView.findViewById<Button>(R.id.dialog_submit)
        val txtInfo = mView.findViewById<TextView>(R.id.txtInfo)
        val txtScore = mView.findViewById<TextView>(R.id.txtScore)
        txtInfo.gravity = Gravity.CENTER_HORIZONTAL
        txtScore.text = (score + 10).toString()
        diagControl.setOnDismissListener {
            timer = Timer()
            timer.scheduleAtFixedRate(object : TimerTask(){
                override fun run() {
                    activity.runOnUiThread {
                        val router = Router()
                        router.switchPage(context, HomeActivity())
                        activity.finishAffinity()
                        timer.cancel()
                    }
                }
            }, 0, 300000)
        }
        activity.txtTime.stop()
        diagControl.setView(mView)
        val alertDialog = diagControl.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCancelable(false)
        okButt.setOnClickListener {
            alertDialog.dismiss()
        }

    }
    private fun addNewScore(context: Context, userName: String, gameType: Int, gameSize: Int, score: Int){
        val mindGameDB = MindGameDB.getInstance(context)
        val scoreTableDao = mindGameDB?.getScoreTableDao()
        val scoreTableEntity = ScoreTableEntity(
            userName = userName,
            gameType = gameType,
            gameSize = gameSize,
            score = score
        )
        thread(start = true){
            scoreTableDao?.addNewItem(scoreTableEntity)
        }

    }
}