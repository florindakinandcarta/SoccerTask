package com.example.soccertask.data

import com.example.soccertask.data.source.Team
import kotlin.random.Random

private const val WIN_POINTS = 3
private const val DRAW_POINTS = 1
private const val GOALS_SCORED = 6
class GenerateResults(private val teams: List<Team>) {
    fun generateResults(fixtures:List<Pair<Team,Team>>):List<Team> {
        for (fixture in fixtures) {
            val homeTeam = fixture.first
            val awayTeam = fixture.second

            val homeGoals = Random.nextInt(GOALS_SCORED)
            val awayGoals = Random.nextInt(GOALS_SCORED)

            when {
                homeGoals > awayGoals -> {
                    homeTeam.points += WIN_POINTS
                    homeTeam.gamesWon += DRAW_POINTS
                    awayTeam.gamesLost += DRAW_POINTS
                }

                homeGoals < awayGoals -> {
                    awayTeam.points += WIN_POINTS
                    awayTeam.gamesWon += DRAW_POINTS
                    homeTeam.gamesLost += DRAW_POINTS
                }

                else -> {
                    homeTeam.points += DRAW_POINTS
                    awayTeam.points += DRAW_POINTS
                    homeTeam.drawGames += DRAW_POINTS
                    awayTeam.drawGames += DRAW_POINTS
                }
            }
        }
        return teams.sortedByDescending { it.points }
    }
}