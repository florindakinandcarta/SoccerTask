package com.example.basketballapp.data

import com.example.basketballapp.data.source.Team
import kotlin.random.Random

private const val WIN_POINTS = 3
private const val GOALS_SCORED = 6
class GenerateResults(private val teamsData: List<Team>) {

    fun generateResults(fixtures: List<Pair<Team, Team>>): List<Team> {
        for (fixture in fixtures) {
            val homeTeam = fixture.first
            val awayTeam = fixture.second

            val homeGoals:Int = Random.nextInt(GOALS_SCORED)
            val awayGoals:Int = Random.nextInt(GOALS_SCORED)
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