package com.pdm.parcial1.UI.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pdm.parcial1.R
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = GameFragmentArgs.fromBundle(it)
            localName.text = safeArgs.localName
            visitorName.text = safeArgs.visitorName
            localScore.text = safeArgs.localScore
            visitorScore.text = safeArgs.visitorScore
            date.text = safeArgs.date
            time.text = safeArgs.time
            winner.text = safeArgs.winner
        }
    }

}
