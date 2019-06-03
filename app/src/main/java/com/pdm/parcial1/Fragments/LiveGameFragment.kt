package com.pdm.parcial1.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

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

        arguments?.let {
            val safeArgs = LiveGameFragmentArgs.fromBundle(it)
            localName.text = safeArgs.localName
            localScore.text = safeArgs.localScore
            visitorName.text = safeArgs.visitorName
            visitorScore.text = safeArgs.visitorScore
            date.text = safeArgs.date
            time.text = safeArgs.time
            winner.text = safeArgs.winner
            stateButton.text = safeArgs.state
        }

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)


        localPlus1.setOnClickListener {
            var score = localScore.text.toString().toInt() + 1

        }

    }

}
