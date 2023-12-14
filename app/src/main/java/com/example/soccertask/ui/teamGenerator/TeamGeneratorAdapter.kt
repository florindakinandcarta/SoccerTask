package com.example.soccertask.ui.teamGenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soccertask.R
import com.example.soccertask.databinding.TeamItemBinding
import com.example.soccertask.data.source.Team
import com.example.soccertask.ui.TeamViewModel

class TeamGeneratorAdapter( private val viewModel: TeamViewModel): ListAdapter<Team, TeamGeneratorAdapter.ViewHolder>(TeamComparator()) {
    inner class ViewHolder(private val binding: TeamItemBinding ):
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TeamItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamGeneratorAdapter.ViewHolder, position: Int) {
            val currentTeam = viewModel.getTeams()[position]
        with(holder.itemView){
            holder.itemView.findViewById<TextView>(R.id.teamName).text = currentTeam.teamName
            holder.itemView.findViewById<TextView>(R.id.teamPoints).text = currentTeam.points.toString()
            val bundle =  Bundle()
            bundle.putString("team_name_data", currentTeam.teamName)
            bundle.putString("team_points", currentTeam.points.toString())
            bundle.putString("team_wins", currentTeam.gamesWon.toString())
            bundle.putString("team_loses", currentTeam.gamesLost.toString())
            bundle.putString("team_draws", currentTeam.drawGames.toString())
            holder.itemView.findViewById<TextView>(R.id.teamName).setOnClickListener {
                holder.itemView.findNavController().navigate(
                    R.id.action_teamGenerator_to_teamDetails,
                    bundle
                )
            }
        }
    }
    override fun getItemCount() = viewModel.getTeams().size
}