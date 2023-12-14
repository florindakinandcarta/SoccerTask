package com.example.soccertask.data

import com.example.soccertask.data.source.Team
import kotlin.random.Random

class GenerateResults(private val teams: List<Team>) {
    fun generateResults(fixtures:List<Pair<Team,Team>>):List<Team> {
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
        return teams
    }
}