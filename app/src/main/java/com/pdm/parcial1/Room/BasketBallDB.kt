package com.pdm.parcial1.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pdm.parcial1.Room.Dao.MatchDao
import com.pdm.parcial1.Room.Entities.Match
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Match::class], version = 7, exportSchema = false)
abstract  class BasketBallDB:RoomDatabase() {

    abstract fun matchDao():MatchDao

    companion object {
        @Volatile
        private var INSTANCE: BasketBallDB? = null

        fun getInstance(appContext: Context, scope: CoroutineScope): BasketBallDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room
                    .databaseBuilder(appContext, BasketBallDB::class.java, "basketball_db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}