package com.pdm.parcial1.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pdm.parcial1.Room.Dao.MatchDao
import com.pdm.parcial1.Room.Entities.Match

class MatchRepository(private val matchDao: MatchDao) {

    fun getAllMatches():LiveData<List<Match>> = matchDao.getAllMatches()

    fun getLiveMatches():LiveData<List<Match>> = matchDao.getLiveMatches()

    fun getMatch(id: Int):LiveData<Match> = matchDao.getMatch(id)

    @WorkerThread
    suspend fun insert(match: Match) = matchDao.insert(match)

    @WorkerThread
    suspend fun deleteAll() = matchDao.deleteAll()

    @WorkerThread
    suspend fun deleteMatch(id: Int) = matchDao.deleteMatch(id)

    @WorkerThread
    suspend fun updateWinner(id:Int, winner:String) = matchDao.updateWinner(id,winner)

    @WorkerThread
    suspend fun updateMatchState(id:Int, state:Int) = matchDao.updateMatchState(id,state)

    @WorkerThread
    suspend fun updateLocalScore(id: Int,score:Int) = matchDao.updateLocalScore(id,score)

    @WorkerThread
    suspend fun updateVisitorScore(id: Int,score: Int) = matchDao.updateVisitorScore(id,score)

}