package com.sandeep.mindgame.memo.datalayer.dao

import androidx.room.*
import com.sandeep.mindgame.memo.datalayer.localdb.entity.ScoreTableEntity

// Creating a Data Access Object
@Dao // this is the Dao interface
interface ScoreTableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewItem(scoreTable: ScoreTableEntity)

    @Delete
    fun removeItems(scoreTable: ScoreTableEntity)

    @Update
    fun updateItem(scoreTable: ScoreTableEntity)

    @Query("SELECT * from score_table where id LIKE :id1")
    fun findSingleItem(id1: Int): ScoreTableEntity

    @Query("SELECT * from score_table")
    fun getAllScoreTable(): List<ScoreTableEntity>
}