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

@Database(entities = [Match::class], version = 1, exportSchema = false)
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
                    .addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class DatabaseCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                scope.launch(Dispatchers.IO){
                    populateDatabase(it.matchDao())
                }
            }
        }

        suspend fun populateDatabase(matchDao: MatchDao){
            matchDao.deleteAll()

            var match = Match("Barcelona", "Real Madrid", 3, 0, "unknown", 1, "14:30", "20/05/2019")
            matchDao.insert(match)
            match = Match("Ajax", "Porto", 2, 2, "unknown", 0, "12:00", "20/05/2015")
            matchDao.insert(match)
        }
    }

}