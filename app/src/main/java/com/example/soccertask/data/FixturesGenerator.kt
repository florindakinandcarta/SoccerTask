package com.example.soccertask.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.soccertask.data.source.Team

class FixturesGenerator{
    fun generateFixtures(teams: List<Team>): List<Pair<Team, Team>> {
        val fixtures: MutableList<Pair<Team, Team>> = mutableListOf()

        for (i in teams.indices) {
            for (j in i + 1 until teams.size) {
                fixtures.add(Pair(teams[i], teams[j]))
                fixtures.add(Pair(teams[j], teams[i]))
            }
        }
        return fixtures
    }
}