package com.example.basketballapp.ui.teamGenerator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basketballapp.databinding.TeamGeneratorBinding
import com.example.basketballapp.ui.TeamViewModel

class TeamFragment: Fragment() {
    private lateinit var binding: TeamGeneratorBinding
    private lateinit var adapter: TeamAdapter
    private  val viewModel: TeamViewModel by viewModels()
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
        context?.let { safeContext ->
            viewModel.fetchTeamsInfo(safeContext.assets.open("teams.json")
                .bufferedReader().use { it.readText() })
            viewModel.teamResults.observe(viewLifecycleOwner) { results ->
                adapter.submitList(results)
                binding.sort.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        adapter.orderByAscending()
                    } else {
                        adapter.orderByDescending()
                    }
                }

            }
            binding.teamsList.layoutManager = LinearLayoutManager(activity)
            adapter = TeamAdapter()
            binding.teamsList.adapter = adapter
        }
    }
}