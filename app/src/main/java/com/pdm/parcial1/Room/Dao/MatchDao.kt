package com.pdm.parcial1.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.parcial1.Room.Entities.Match

@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: Match)

    @Query("select * from `match` order by date desc")
    fun getAllMatches():LiveData<List<Match>>

    @Query("select * from `match` where state=1 order by date desc")
    fun getLiveMatches():LiveData<List<Match>>

    @Query("delete from `match`")
    suspend fun deleteAll()

    @Query("delete from `match`where id=:idMatch")
    suspend fun deleteMatch(idMatch: Int)

    @Query("update `match` set state=:state where id=:idMatch")
    suspend fun updateMatchState(idMatch:Int, state:Int)

    @Query("update `match` set winner=:winner where id=:idMatch")
    suspend fun updateWinner(idMatch:Int, winner:String)

}