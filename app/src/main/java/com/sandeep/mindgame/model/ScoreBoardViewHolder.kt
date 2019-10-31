package com.sandeep.mindgame.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.mindgame.R
import com.sandeep.mindgame.memo.datalayer.GameTypeEnum
import com.sandeep.mindgame.memo.datalayer.localdb.entity.ScoreTableEntity


class ScoreBoardViewHolder(parent: ViewGroup):
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.score_board, parent, false))
{
    var txtUserName: TextView = itemView.findViewById(R.id.scoreUser)
    var txtGameType: TextView = itemView.findViewById(R.id.txtGameType)
    var txtGameSize: TextView = itemView.findViewById(R.id.txtGameSize)
    var txtScore: TextView = itemView.findViewById(R.id.userScore)


    @SuppressLint("SetTextI18n")
    fun bind(scoreList: ScoreTableEntity){
        txtUserName.text = scoreList.userName
        txtGameSize.text = scoreList.gameSize.toString()+ "kare"
        when (scoreList.gameType){

            GameTypeEnum.ANIMAL.value ->{ txtGameType.text = "Animal"}
            GameTypeEnum.OBJECT.value -> { txtGameType.text = "Object"}
            GameTypeEnum.PLANT.value -> { txtGameType.text = "Plant"}
            GameTypeEnum.PLANT.value -> { txtGameType.text = "Plant"}
        }
        txtScore.text = scoreList.score.toString()
    }
}

