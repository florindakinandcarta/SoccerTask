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
    fun orderByDescending(){
        submitList(currentList.sortedBy { it.points })
    }
    fun orderByAscending(){
        submitList(currentList.sortedByDescending { it.points })
    }
    inner class ViewHolder(private val binding: TeamItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(team: Team){
                binding.apply {
                    teamName.text = team.teamName
                    teamPoints.text = team.points.toString()
                    teamCity.text = team.location
                    Picasso.get().load(team.imgUrl) .into(teamLogo)
                    val bundle = Bundle().apply {
                        putString("team_name_data", team.teamName)
                        putString("team_points", team.points.toString())
                        putString("simple_name", team.simpleName)
                        putString("city", team.location)
                        putString("imgUrl", team.imgUrl)
                    }
                    teamName.setOnClickListener {
                        it.findNavController().navigate(
                            R.id.action_teamGenerator_to_teamDetails,
                            bundle
                        )
                    }
                }
            }
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TeamItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentItem = getItem(position)
            holder.bind(currentItem)
    }

    override fun getItemCount() = currentList.size
}