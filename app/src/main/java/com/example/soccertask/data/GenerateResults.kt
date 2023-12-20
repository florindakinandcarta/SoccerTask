package com.example.soccertask.data

import com.example.soccertask.data.source.Team
import kotlin.random.Random

private const val WIN_POINTS = 3
private const val GOALS_SCORED = 6
class GenerateResults(private val teamsData: List<Team>) {

    fun generateResults(fixtures: List<Pair<Team, Team>>): List<Team> {
        for (fixture in fixtures) {
            val homeTeam = fixture.first
            val awayTeam = fixture.second

            val homeGoals:Int = Random.nextInt(0,6)
            val awayGoals:Int = Random.nextInt(0,6)
            homeTeam.goals = homeGoals
            awayTeam.goals = awayGoals
            when {
                homeGoals > awayGoals -> {
                    homeTeam.points += WIN_POINTS
                }

                homeGoals < awayGoals -> {
                    awayTeam.points += WIN_POINTS
                }

                else -> {
                    homeTeam.points++
                    awayTeam.points++
                }
            }

        }
        return teamsData
    }
}
