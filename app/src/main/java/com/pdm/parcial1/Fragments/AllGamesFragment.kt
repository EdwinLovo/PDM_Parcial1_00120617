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
import com.pdm.parcial1.Adapters.AllMatchesAdapter

import com.pdm.parcial1.R
import com.pdm.parcial1.Room.Entities.Match
import com.pdm.parcial1.ViewModel.MatchViewModel
import kotlinx.android.synthetic.main.fragment_all_games.view.*

class AllGamesFragment : Fragment() {

    lateinit var viewModel:MatchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_games, container, false)
        init(view)
        return view
    }

    fun init(view: View) {
        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        var adapter = object : AllMatchesAdapter(view.context){
            override fun setClickListenerToMatch(holder: AllMatchesViewHolder, match: Match) {
                holder.container.setOnClickListener {
                    if (match.state==1){
                        val nextAction = AllGamesFragmentDirections.nextAction2(match.local,match.localScore.toString(),match.visitorScore.toString(),match.visitor,match.date,match.time,match.winner,match.state.toString())
                        Navigation.findNavController(it).navigate(nextAction)
                    } else {
                        val nextAction = AllGamesFragmentDirections.nextAction(match.local,match.visitor,match.localScore.toString(),match.visitorScore.toString(),match.date,match.time, match.winner,match.state.toString())
                        Navigation.findNavController(it).navigate(nextAction)
                    }
                }
            }
        }
        val recyclerView = view.allMatchesRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        viewModel.allMatches.observe(this, Observer {matches ->
            matches?.let { adapter.setMatches(it) }
        })
    }

}
