package com.pdm.parcial1.UI.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.pdm.parcial1.R
import com.pdm.parcial1.ViewModel.MatchViewModel
import kotlinx.android.synthetic.main.fragment_live_game.*

class LiveGameFragment : Fragment() {

    lateinit var viewModel: MatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var idMatch = 0

        arguments?.let {
            val safeArgs = LiveGameFragmentArgs.fromBundle(it)
            localName.text = safeArgs.localName
            localScore.text = safeArgs.localScore
            visitorName.text = safeArgs.visitorName
            visitorScore.text = safeArgs.visitorScore
            date.text = safeArgs.date
            time.text = safeArgs.time
            winner.text = "Winner: Unkown yet"
            stateButton.text = "END MATCH"
            idMatch = safeArgs.idMatch.toInt()
        }

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        viewModel.match?.observe(this, Observer { match ->
            match?.let {
                visitorScore.text = it.visitorScore.toString()
                localScore.text = it.localScore.toString()
            }
        })

        stateButton.setOnClickListener {
            viewModel.updateMatchState(idMatch,0)
            Navigation.findNavController(it).navigate(R.id.popup_action)
            Toast.makeText(it.context, "Match finished", Toast.LENGTH_LONG).show()
        }

        localPlus1.setOnClickListener {
            var score = localScore.text.toString().toInt() + 1
            viewModel.updateLocalScore(idMatch,score)
            localScore.text = score.toString()
        }

        localPlus2.setOnClickListener {
            var score = localScore.text.toString().toInt() + 2
            viewModel.updateLocalScore(idMatch,score)
            localScore.text = score.toString()
        }

        localPlus3.setOnClickListener {
            var score = localScore.text.toString().toInt() + 3
            viewModel.updateLocalScore(idMatch,score)
            localScore.text = score.toString()
        }

        visitorPlus1.setOnClickListener {
            var score = visitorScore.text.toString().toInt() + 1
            viewModel.updateVisitorScore(idMatch,score)
            visitorScore.text = score.toString()
        }

        visitorPlus2.setOnClickListener {
            var score = visitorScore.text.toString().toInt() + 2
            viewModel.updateVisitorScore(idMatch,score)
            visitorScore.text = score.toString()
        }

        visitorPlus3.setOnClickListener {
            var score = visitorScore.text.toString().toInt() + 3
            viewModel.updateVisitorScore(idMatch,score)
            visitorScore.text = score.toString()
        }

    }

}
