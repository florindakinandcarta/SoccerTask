package com.example.soccertask.data

import com.example.soccertask.data.source.Team
import kotlin.random.Random

 class TeamGenerator{
    fun generateTeams(): List<Team> {
        val teams = mutableListOf<Team>()
        while (teams.size < 20) {
            val newTeam = Team(
                "Team ${
                    Random.nextInt(
                        20
                    )
                }"
            )
            teams.add(newTeam)
        }
        return teams
    }
}
