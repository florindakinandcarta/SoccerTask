package com.example.basketballapp.data.source

import com.google.gson.annotations.SerializedName
data class TeamData(
    @SerializedName("teams_list") var teamsList: ArrayList<Team> = arrayListOf()
)
data class Team(
    @SerializedName("teamName") val teamName: String? = null,
    @SerializedName("points") var points: Int = 0,
    @SerializedName("goals") var goals: Int = 0,
    @SerializedName("games_lost") var gamesLost: Int = 0,
    @SerializedName("draw_games") var drawGames: Int = 0,
    @SerializedName("teamId") var teamId: Int? = null,
    @SerializedName("abbreviation") var abbreviation: String? = null,
    @SerializedName("simpleName") var simpleName: String?= null,
    @SerializedName("location") var location: String?= null,
    @SerializedName("imgUrl") var imgUrl: String?= null,
    @SerializedName("position") var position: Int = 0,
)