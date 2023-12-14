package com.example.soccertask.ui.teamGenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soccertask.data.FixturesGenerator
import com.example.soccertask.databinding.TeamGeneratorBinding
import com.example.soccertask.data.TeamGenerator
import com.example.soccertask.data.GenerateResults
import com.example.soccertask.ui.TeamViewModel

class TeamFragment: Fragment() {
    private lateinit var binding: TeamGeneratorBinding
    private lateinit var adapter: TeamGeneratorAdapter
    private val viewModel: TeamViewModel by viewModels()


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
        binding.teamsList.layoutManager = LinearLayoutManager(requireActivity())
        adapter  = TeamGeneratorAdapter(viewModel)
        binding.teamsList.adapter = adapter
    }
}