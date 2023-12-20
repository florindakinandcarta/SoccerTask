package com.example.soccertask.ui.teamDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccertask.data.source.Team
import com.example.soccertask.databinding.TeamDetailsBinding
import com.example.soccertask.ui.TeamViewModel

class TeamDetails: Fragment() {
    private lateinit var binding: TeamDetailsBinding
    private lateinit var adapter: TeamDetailsAdapter
    val args: TeamDetailsArgs by navArgs()
    private  val viewModel: TeamViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamDetailsBinding.inflate(
            layoutInflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { safeContext ->
            viewModel.fetchTeamsInfo(safeContext.assets.open("teams.json")
                .bufferedReader().use { it.readText() })
            viewModel.teamResults.observe(viewLifecycleOwner) { results ->
                val fixtures: List<Pair<Team, Team>> = viewModel.generateFixtures(results)
                adapter.filterGamesPlayed(args.teamNameData, fixtures)
            }
        }
            with(binding) {
                gamesPlayedList.layoutManager = LinearLayoutManager(activity)
                adapter = TeamDetailsAdapter()
                gamesPlayedList.adapter = adapter

                teamNameDetails.text = args.teamNameData
                simpleName.text = args.simpleName
                teamCityDetails.text = args.city
                teamPointsDetails.text = args.teamPoints
            }
        }
    }

