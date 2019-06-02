package com.pdm.parcial1.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.pdm.parcial1.R
import com.pdm.parcial1.Room.Entities.Match
import com.pdm.parcial1.ViewModel.MatchViewModel
import kotlinx.android.synthetic.main.fragment_new_game.*
import java.lang.Exception

class NewGameFragment : Fragment() {

    lateinit var viewModel:MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        createMatch.setOnClickListener {
            var stateInt:Int
            if (state.text.toString().toUpperCase()=="LIVE"){
                stateInt=1
            } else if (state.text.toString().toUpperCase()=="FINISHED") {
                stateInt=0
            } else {
                stateInt = 2
            }
            val match = Match(localName.text.toString(), visitorName.text.toString(),
                    localScore.text.toString().toInt(), visitorScore.text.toString().toInt(),
                    "unkown", stateInt, time.text.toString(), date.text.toString())

            try {
                viewModel.insertMatch(match)
                localName.setText("")
                localScore.setText("")
                visitorName.setText("")
                visitorScore.setText("")
                date.setText("")
                time.setText("")
                state.setText("")
                val nextAction = NewGameFragmentDirections.nextAction(match.local, match.visitor, match.localScore.toString(), match.visitorScore.toString(), match.date, match.time, match.winner, match.state.toString())
                Navigation.findNavController(it).navigate(nextAction)
            } catch (e:Exception){}
        }
    }

}
