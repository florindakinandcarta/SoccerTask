package com.example.soccertask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soccertask.data.FixturesGenerator
import com.example.soccertask.data.GenerateResults
import com.example.soccertask.data.source.Team
import com.example.soccertask.data.source.TeamData
import com.google.gson.Gson

class TeamViewModel : ViewModel() {
    private val _teamResults = MutableLiveData<List<Team>>()
    val teamResults: LiveData<List<Team>> get() = _teamResults



    fun fetchTeamsInfo(filePath: String) {
        val teamData = parseTeamData(filePath)
        val updatedTeamsData = updateTeams(teamData.teamsList)
        val fixtures = generateFixtures(updatedTeamsData)
        val results = generateResults(updatedTeamsData, fixtures)

        _teamResults.value = results
    }
    private fun parseTeamData(filePath: String): TeamData {
        val gson = Gson()
        return gson.fromJson(filePath, TeamData::class.java)
    }

    private fun updateTeams(teamsList: List<Team>): MutableList<Team> {
        return teamsList.toMutableList()
    }

     fun generateFixtures(teams: List<Team>): List<Pair<Team, Team>> {
        return FixturesGenerator().generateFixtures(teams)
    }

    private fun generateResults(teams: List<Team>, fixtures: List<Pair<Team, Team>>): List<Team> {
        return GenerateResults(teams).generateResults(fixtures)
    }


}
