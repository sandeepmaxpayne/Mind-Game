package com.sandeep.mindgame.memo.datalayer.localdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sandeep.mindgame.memo.datalayer.dao.ScoreTableDao
import com.sandeep.mindgame.memo.datalayer.localdb.entity.ScoreTableEntity

//ScoreTableEntity::class to refer this class as  a variable
@Database(entities = arrayOf(ScoreTableEntity::class), version = 1)
abstract class MindGameDB : RoomDatabase(){

    abstract fun getScoreTableDao(): ScoreTableDao

    companion object{
        private var instance: MindGameDB? = null

        fun getInstance(context: Context): MindGameDB?{
            synchronized(this@Companion){
                if (instance == null){
                    //create DB
                    instance = Room.databaseBuilder(context.applicationContext, MindGameDB::class.java, "MindGame")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}