package com.example.soccertask.ui

import androidx.lifecycle.ViewModel
import com.example.soccertask.data.FixturesGenerator
import com.example.soccertask.data.GenerateResults
import com.example.soccertask.data.TeamGenerator
import com.example.soccertask.data.source.Team

class TeamViewModel: ViewModel() {

    private val teamsGenerator = TeamGenerator()
    private val fixtureGenerator = FixturesGenerator()

    private val generatedTeams = teamsGenerator.generateTeams()
    private val results = GenerateResults(generatedTeams)
    private val fixturesGenerator = fixtureGenerator.generateFixtures(generatedTeams)
    private var teamResults = results.generateResults(fixturesGenerator)

    fun getTeams(): List<Team>{
        return teamResults
    }
}