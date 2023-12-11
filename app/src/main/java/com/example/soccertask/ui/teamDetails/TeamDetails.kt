package com.example.soccertask.ui.teamDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.soccertask.R
import com.example.soccertask.databinding.TeamDetailsBinding

class TeamDetails: Fragment() {
    private lateinit var binding: TeamDetailsBinding
    private val args: TeamDetailsArgs by navArgs()


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
        with(binding){
            teamNameDetails.text = args.teamNameData
            teamPointsDetails.text = args.teamPoints.toString()
            gamesWonPoints.text = args.teamWins.toString()
            gamesLostPoints.text = args.teamLoses.toString()
            gamesDrawPoints.text = args.teamDraws.toString()
            back.setOnClickListener{
                findNavController().navigate(
                    R.id.action_teamDetails_to_teamGenerator
                )
            }
        }
    }
}
