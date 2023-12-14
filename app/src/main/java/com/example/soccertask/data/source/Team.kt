package com.example.soccertask.data.source

data class Team(
    val teamName: String,
    var points: Int = 0,
    var gamesWon: Int = 0,
    var gamesLost: Int = 0,
    var drawGames: Int = 0
)
