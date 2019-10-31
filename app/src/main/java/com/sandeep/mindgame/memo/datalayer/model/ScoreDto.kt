package com.sandeep.mindgame.memo.datalayer.model

// Data Class
data class ScoreDto(val id: Int,
                    val userName: String,
                    val gameType:Int,
                    val gameSize: Int,
                    val score: Int)