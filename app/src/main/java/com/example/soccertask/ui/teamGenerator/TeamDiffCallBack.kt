package com.example.soccertask.ui.teamGenerator

import androidx.recyclerview.widget.DiffUtil
import com.example.soccertask.data.source.Team

class TeamDiffCallBack: DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem == newItem
    }
}