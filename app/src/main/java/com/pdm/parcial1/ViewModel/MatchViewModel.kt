package com.pdm.parcial1.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pdm.parcial1.Repository.MatchRepository
import com.pdm.parcial1.Room.BasketBallDB
import com.pdm.parcial1.Room.Entities.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(app:Application):AndroidViewModel(app) {

    private val matchRepository:MatchRepository
    val allMatches:LiveData<List<Match>>
    val allLiveMatches:LiveData<List<Match>>

    init {
        val matchDao = BasketBallDB.getInstance(app, viewModelScope).matchDao()
        matchRepository = MatchRepository(matchDao)
        allMatches = matchRepository.getAllMatches()
        allLiveMatches = matchRepository.getLiveMatches()
    }

    fun insertMatch(match: Match) = viewModelScope.launch(Dispatchers.IO){
        matchRepository.insert(match)
    }

    fun updateWinner(id:Int,winner:String) = viewModelScope.launch(Dispatchers.IO){
        matchRepository.updateWinner(id,winner)
    }

    fun updateMatchState(id: Int,state:Int) = viewModelScope.launch(Dispatchers.IO){
        matchRepository.updateMatchState(id,state)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO){
        matchRepository.deleteAll()
    }

    fun deleteMatch(id: Int) = viewModelScope.launch(Dispatchers.IO){
        matchRepository.deleteMatch(id)
    }

}