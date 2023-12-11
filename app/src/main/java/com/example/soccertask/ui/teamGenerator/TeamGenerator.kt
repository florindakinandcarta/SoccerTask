package com.example.soccertask.ui.teamGenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccertask.data.Team
import com.example.soccertask.databinding.TeamGeneratorBinding
import kotlin.random.Random

class TeamGenerator: Fragment() {
    private lateinit var binding: TeamGeneratorBinding
    private var teamsListItems = generateTeams()
    private val adapter = TeamGeneratorAdapter(teamsListItems)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamGeneratorBinding.inflate(
            layoutInflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.teamsList.layoutManager = LinearLayoutManager(activity)
        binding.teamsList.adapter = adapter
        with(binding){
            val fixtures = generateFixtures(teamsListItems)
            generateResults(fixtures)
            sort.setOnClickListener{
                teamsListItems = teamsListItems.sortedByDescending { it.points }.toMutableList()
                adapter.updateTeamsList(teamsListItems)
            }
        }
    }
    private fun generateTeams(): MutableList<Team> {
        val teams = mutableListOf<Team>()
        while (teams.size < 20) {
            val newTeam = Team("Team ${Random.nextInt(20)}")
            teams.add(newTeam)
        }
        return teams
    }
    private fun generateFixtures(teams: List<Team>): List<Pair<Team, Team>> {
        val fixtures = mutableListOf<Pair<Team, Team>>()
        for (i in teams.indices) {
            for (j in i + 1 until teams.size) {
                fixtures.add(Pair(teams[i], teams[j]))
                fixtures.add(Pair(teams[j], teams[i]))
            }
        }
        return fixtures
    }

    private fun generateResults(fixtures: List<Pair<Team, Team>>) {
        for (fixture in fixtures) {
            val homeTeam = fixture.first
            val awayTeam = fixture.second

            val homeGoals = Random.nextInt(6)
            val awayGoals = Random.nextInt(6)

            when {
                homeGoals > awayGoals -> {
                    homeTeam.points += 3
                    homeTeam.gamesWon += 1
                    awayTeam.gamesLost += 1
                }

                homeGoals < awayGoals -> {
                    awayTeam.points += 3
                    awayTeam.gamesWon += 1
                    homeTeam.gamesLost += 1
                }

                else -> {
                    homeTeam.points += 1
                    awayTeam.points += 1
                    homeTeam.drawGames += 1
                    awayTeam.drawGames += 1
                }
            }
        }
    }
}