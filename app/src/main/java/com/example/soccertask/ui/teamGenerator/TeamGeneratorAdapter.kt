package com.example.soccertask.ui.teamGenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.soccertask.R
import com.example.soccertask.data.Team
import com.example.soccertask.databinding.TeamItemBinding

class TeamGeneratorAdapter(private var teams:List<Team>): RecyclerView.Adapter<TeamGeneratorAdapter.ViewHolder>() {
    inner class ViewHolder( val binding: TeamItemBinding ):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TeamItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return teams.size
    }
    fun updateTeamsList(updatedList: List<Team>){
        teams = updatedList
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentTeam = teams[position]
        with(holder.binding){
            teamName.text = currentTeam.teamName
            teamPoints.text = currentTeam.points.toString()
            val bundle =  Bundle()
            bundle.putString("team_name_data", currentTeam.teamName)
            bundle.putString("team_points", currentTeam.points.toString())
            bundle.putString("team_wins", currentTeam.gamesWon.toString())
            bundle.putString("team_loses", currentTeam.gamesLost.toString())
            bundle.putString("team_draws", currentTeam.drawGames.toString())
            teamName.setOnClickListener {
                holder.itemView.findNavController().navigate(
                    R.id.action_teamGenerator_to_teamDetails,
                    bundle
                )
            }
        }
    }
}