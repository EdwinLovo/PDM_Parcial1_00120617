package com.pdm.parcial1.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdm.parcial1.R
import com.pdm.parcial1.Room.Entities.Match
import kotlinx.android.synthetic.main.match_info.view.*

abstract class AllMatchesAdapter internal constructor(context: Context):RecyclerView.Adapter<AllMatchesAdapter.AllMatchesViewHolder>(){

    private val inflater = LayoutInflater.from(context)
    private var matches = emptyList<Match>()

    abstract fun setClickListenerToMatch(holder: AllMatchesViewHolder, match: Match)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMatchesViewHolder {
        val itemView = inflater.inflate(R.layout.match_info, parent,false)
        return AllMatchesViewHolder(itemView)
    }

    override fun getItemCount() = matches.size

    override fun onBindViewHolder(holder: AllMatchesViewHolder, position: Int) {
        val currentMatch = matches[position]

        holder.localName.text = currentMatch.local
        holder.visitorName.text = currentMatch.visitor
        holder.localScore.text = currentMatch.localScore.toString()
        holder.visitorScore.text = currentMatch.visitorScore.toString()
        holder.date.text = currentMatch.date
        holder.time.text = currentMatch.time
        holder.winner.text = currentMatch.winner
        holder.state.text = currentMatch.state.toString()
        if (holder.state.text=="1"){
            Log.d("CODIGO", "sdfsf")
        }

        setClickListenerToMatch(holder,currentMatch)
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
    }

    internal fun setMatches(matches:List<Match>){
        this.matches = matches
        notifyDataSetChanged()
    }

}