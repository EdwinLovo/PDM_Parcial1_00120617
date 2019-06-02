package com.pdm.parcial1.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.parcial1.Adapters.LiveGamesAdapter

import com.pdm.parcial1.R
import com.pdm.parcial1.Room.Entities.Match
import com.pdm.parcial1.ViewModel.MatchViewModel
import kotlinx.android.synthetic.main.fragment_live_games.view.*

class LiveGamesFragment : Fragment() {

    lateinit var viewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_live_games, container, false)
        init(view)
        return view
    }

    fun init(view: View) {
        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        var adapter = object : LiveGamesAdapter(view.context){
            override fun setClickListenerToLiveGames(holder: LiveMatchesViewHolder, match: Match) {
                holder.container.setOnClickListener {
                    val nextAction = AllGamesFragmentDirections.nextAction(match.local, match.visitor, match.localScore.toString(), match.visitorScore.toString(), match.date, match.time, match.winner, match.state.toString())
                    Navigation.findNavController(it).navigate(nextAction)
                }
            }
        }
        val recyclerView = view.liveGamesRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        viewModel.allLiveMatches.observe(this, Observer { matches ->
            matches?.let { adapter.setMatches(it) }
        })
    }

}
