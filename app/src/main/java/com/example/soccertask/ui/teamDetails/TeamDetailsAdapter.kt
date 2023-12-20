package com.example.soccertask.ui.teamDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.soccertask.data.source.Team
import com.example.soccertask.databinding.TeamDetailsItemBinding
import com.example.soccertask.ui.teamGenerator.TeamDiffCallBack

class TeamDetailsAdapter: ListAdapter<Team, TeamDetailsAdapter.ViewHolder>(TeamDiffCallBack()) {
    private var filteredTeams: List<Pair<Team, Team>> = emptyList()
    private var fixtures: List<Pair<Team, Team>> = emptyList()
    fun filterGamesPlayed(specificTeam: String?, fixturesList: List<Pair<Team, Team>>){
        fixtures = fixturesList
        filteredTeams = fixturesList.filter { fixture ->
            fixture.first.teamName == specificTeam || fixture.second.teamName == specificTeam
        }
    }
    inner class ViewHolder(val binding: TeamDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TeamDetailsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder.binding) {
                firstTeamNameItem.text = filteredTeams[position].first.teamName
                secondTeamNameItem.text = filteredTeams[position].second.teamName
                firstTeamPointItem.text = filteredTeams[position].first.goals.toString()
                secondTeamPointItem.text = filteredTeams[position].second.goals.toString()
        }

    }
    override fun getItemCount() = filteredTeams.size
}

