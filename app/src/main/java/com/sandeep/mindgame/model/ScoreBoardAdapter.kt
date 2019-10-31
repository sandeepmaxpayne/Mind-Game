package com.sandeep.mindgame.model

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.mindgame.memo.datalayer.localdb.entity.ScoreTableEntity


class ScoreBoardAdapter(val scoreList: List<ScoreTableEntity>): RecyclerView.Adapter<ScoreBoardViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreBoardViewHolder = ScoreBoardViewHolder(parent)

    override fun getItemCount(): Int {
        scoreList.let {
            return it.size
        }

    }

    override fun onBindViewHolder(holder: ScoreBoardViewHolder, position: Int) {
        scoreList.let {
            holder.bind(it[position])
        }
    }
}