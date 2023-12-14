package com.example.soccertask.data

import com.example.soccertask.data.source.Team
import kotlin.random.Random

private const val NUMBER_OF_TEAMS = 20
 class TeamGenerator{
    fun generateTeams(): List<Team> {
        val teams = mutableListOf<Team>()
        while (teams.size < NUMBER_OF_TEAMS) {
            val newTeam = Team(
                buildString {
                    append("Team ")
                    append(
                        Random.nextInt(
                        NUMBER_OF_TEAMS
                    )
                    )
                }
            )
            teams.add(newTeam)
        }
        return teams
    }
}
