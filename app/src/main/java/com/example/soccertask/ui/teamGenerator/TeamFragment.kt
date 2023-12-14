package com.example.soccertask.ui.teamGenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccertask.data.FixturesGenerator
import com.example.soccertask.databinding.TeamGeneratorBinding
import com.example.soccertask.data.TeamGenerator
import com.example.soccertask.data.GenerateResults

class TeamFragment: Fragment() {
    private lateinit var binding: TeamGeneratorBinding
    private lateinit var adapter: TeamGeneratorAdapter
    private val teamsGenerator = TeamGenerator()
    private val fixtureGenerator = FixturesGenerator()
    private val generatedTeams = teamsGenerator.generateTeams()
    private val results = GenerateResults(generatedTeams)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamGeneratorBinding.inflate(
            layoutInflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.teamsList.layoutManager = LinearLayoutManager(activity)
        adapter  = TeamGeneratorAdapter()

        val fixturesGenerator = fixtureGenerator.generateFixtures(generatedTeams)
        val results = results.generateResults(fixturesGenerator)

        adapter.setTeams(results)
        binding.teamsList.adapter = adapter
        binding. sort.setOnClickListener {
           adapter.sortByPointsDescending()
        }
    }
}