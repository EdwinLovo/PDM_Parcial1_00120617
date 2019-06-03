package com.pdm.parcial1.UI.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdm.parcial1.R
import com.pdm.parcial1.Room.Entities.Match
import kotlinx.android.synthetic.main.match_info.view.*

abstract class AllMatchesAdapter internal constructor(context: Context):RecyclerView.Adapter<AllMatchesAdapter.AllMatchesViewHolder>(){

    private val inflater = LayoutInflater.from(context)
    private var matches = emptyList<Match>()

    abstract fun setClickListenerToMatch(holder: AllMatchesViewHolder, match: Match, winner:String)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMatchesViewHolder {
        val itemView = inflater.inflate(R.layout.match_info, parent,false)
        return AllMatchesViewHolder(itemView)
    }

    override fun getItemCount() = matches.size

    override fun onBindViewHolder(holder: AllMatchesViewHolder, position: Int) {
        val currentMatch = matches[position]

        var matchWinner:String

        if (currentMatch.localScore>currentMatch.visitorScore){
            matchWinner = currentMatch.local
        } else if (currentMatch.localScore<currentMatch.visitorScore){
            matchWinner = currentMatch.visitor
        } else {
            matchWinner = "Draw"
        }

        holder.localName.text = currentMatch.local
        holder.visitorName.text = currentMatch.visitor
        holder.localScore.text = currentMatch.localScore.toString()
        holder.visitorScore.text = currentMatch.visitorScore.toString()
        holder.date.text = currentMatch.date
        holder.time.text = currentMatch.time

        if (currentMatch.state==1){
            holder.winner.text = "Winner: Unknown yet"
            holder.state.text = "LIVE"
            //holder.state.isEnabled = true
        } else {
            holder.winner.text = "Winner: ${matchWinner}"
            holder.state.text = "FINISHED"
            //holder.state.isEnabled = false
        }

        setClickListenerToMatch(holder,currentMatch, matchWinner)
    }

    inner class AllMatchesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val localName:TextView = itemView.localName
        val visitorName:TextView = itemView.visitorName
        val localScore:TextView = itemView.localScore
        val visitorScore:TextView = itemView.visitorScore
        val date:TextView = itemView.date
        val time:TextView = itemView.time
        val winner:TextView = itemView.winner
        val state:Button = itemView.stateButton
        val container:LinearLayout = itemView.matchContainer
        val deleteButton:ImageButton = itemView.deleteButton
    }

    internal fun setMatches(matches:List<Match>){
        this.matches = matches
        notifyDataSetChanged()
    }

}