package com.example.basketballapp.ui.teamDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basketballapp.R
import com.example.basketballapp.data.source.Team
import com.example.basketballapp.databinding.TeamDetailsItemBinding
import com.example.basketballapp.ui.teamGenerator.TeamDiffCallBack
import com.squareup.picasso.Picasso

class TeamDetailsAdapter(private val specificTeam: String?): ListAdapter<Team, TeamDetailsAdapter.ViewHolder>(TeamDiffCallBack()) {
    private var filteredTeams: List<Pair<Team, Team>> = emptyList()
    private var fixtures: List<Pair<Team, Team>> = emptyList()
    fun filterGamesPlayed( fixturesList: List<Pair<Team, Team>>){
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
                val firstTeamLogo = filteredTeams[position].first.imgUrl
                val secondTeamLogo = filteredTeams[position].second.imgUrl
                Picasso.get().load(firstTeamLogo) .into(firstTeam)
                Picasso.get().load(secondTeamLogo).into(secondTeam)
                if(specificTeam == filteredTeams[position].first.teamName){
                    if(filteredTeams[position].first.goals > filteredTeams[position].second.goals){
                        listContraint.setBackgroundResource(R.drawable.greenoverlay)
                    }else if(filteredTeams[position].first.goals == filteredTeams[position].second.goals){
                        listContraint.setBackgroundResource(R.drawable.yellowoverlay)
                    }
                    else{
                        listContraint.setBackgroundResource(R.drawable.redoverlay)
                    }
                }
                else if(specificTeam == filteredTeams[position].second.teamName){
                    if(filteredTeams[position].second.goals > filteredTeams[position].first.goals){
                        listContraint.setBackgroundResource(R.drawable.greenoverlay)
                    }else if(filteredTeams[position].first.goals == filteredTeams[position].second.goals){
                        listContraint.setBackgroundResource(R.drawable.yellowoverlay)
                    }
                    else{
                        listContraint.setBackgroundResource(R.drawable.redoverlay)
                    }
                }
        }

    }
    override fun getItemCount() = filteredTeams.size
}

