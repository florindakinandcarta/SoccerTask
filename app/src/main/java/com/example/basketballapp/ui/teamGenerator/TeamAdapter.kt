package com.example.basketballapp.ui.teamGenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basketballapp.R
import com.example.basketballapp.data.source.Team
import com.example.basketballapp.databinding.TeamItemBinding
import com.squareup.picasso.Picasso


class TeamAdapter: ListAdapter<Team, TeamAdapter.ViewHolder>(TeamDiffCallBack()) {
    private var teams: List<Team> = listOf()

    fun submitTeamList(newList: List<Team>) {
        teams = newList
        submitList(teams)
    }
    fun orderByDescending(newList: List<Team>){
        teams = newList.sortedByDescending { it.points }
        assignPositions()
        submitList(teams)
    }
    fun orderByAscending(newList: List<Team>){
        teams = newList.sortedBy{ it.points }
        submitList(teams)
    }

    private fun assignPositions() {
        teams.forEachIndexed { index, team ->
            team.position = index + 1
        }
    }
    inner class ViewHolder( val binding: TeamItemBinding):
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
            teamStanding.text= teams[position].position.toString()
            teamCity.text = teamList.location
            Picasso.get().load(teamList.imgUrl) .into(teamLogo)
            val bundle = Bundle().apply {
                putString("team_name_data", teamList.teamName)
                putString("team_points", teamList.points.toString())
                putString("simple_name", teamList.simpleName)
                putString("city", teamList.location)
                putString("imgUrl", teamList.imgUrl)
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