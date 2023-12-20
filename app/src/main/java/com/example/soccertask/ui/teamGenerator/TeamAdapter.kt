package com.example.soccertask.ui.teamGenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soccertask.R
import com.example.soccertask.databinding.TeamItemBinding
import com.example.soccertask.data.source.Team

class TeamAdapter: ListAdapter<Team, TeamAdapter.ViewHolder>(TeamDiffCallBack()) {
    private var teams: List<Team> = listOf()

    fun submitTeamList(newList: List<Team>) {
        teams = newList
        submitList(newList)
    }
    inner class ViewHolder( val binding: TeamItemBinding ):
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TeamItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teamList = teams[position]
        with(holder.binding){
           teamName.text = teamList.teamName
            teamPoints.text = teamList.points.toString()
            teamStanding.text= (position + 1).toString()
            teamCity.text = teamList.location
            val bundle = Bundle().apply {
                putString("team_name_data", teamList.teamName)
                putString("team_points", teamList.points.toString())
                putString("simple_name", teamList.simpleName)
                putString("city", teamList.location)
            }
            teamName.setOnClickListener {
                it.findNavController().navigate(
                    R.id.action_teamGenerator_to_teamDetails,
                    bundle
                )
            }
        }
    }
    override fun getItemCount() = teams.size
}