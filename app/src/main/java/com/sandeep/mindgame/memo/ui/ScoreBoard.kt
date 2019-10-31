package com.sandeep.mindgame.memo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandeep.mindgame.R
import com.sandeep.mindgame.memo.datalayer.localdb.db.MindGameDB
import com.sandeep.mindgame.memo.datalayer.localdb.entity.ScoreTableEntity
import com.sandeep.mindgame.model.ScoreBoardAdapter
import kotlinx.android.synthetic.main.activity_score_board.*
import kotlin.concurrent.thread

class ScoreBoard : AppCompatActivity() {

    var scoreList: List<ScoreTableEntity>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board)

        val mindGameDb = MindGameDB.getInstance(this)
        val scoreTableDao = mindGameDb!!.getScoreTableDao()
        thread(start = true){
            scoreList = scoreTableDao.getAllScoreTable()
            with(recyclerSCoreBoard){
                adapter = ScoreBoardAdapter(scoreList!!)
                layoutManager = LinearLayoutManager(this@ScoreBoard)
            }
        }
    }
}
