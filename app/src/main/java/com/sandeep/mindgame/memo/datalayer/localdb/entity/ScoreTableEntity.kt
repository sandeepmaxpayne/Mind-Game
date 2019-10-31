package com.sandeep.mindgame.memo.datalayer.localdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class ScoreTableEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "userName") val userName: String,
    @ColumnInfo(name = "gameType") val gameType: Int,
    @ColumnInfo(name = "gameSize") val gameSize: Int,
    @ColumnInfo(name = "score") val score: Int
)