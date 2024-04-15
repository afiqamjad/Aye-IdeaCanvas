package com.bignerdranch.android.aye_ideacanvas.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomepageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val homepages: List<Homepage>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = homepages.size

    override fun createFragment(position: Int): Fragment {
        // Create a new instance of the fragment and pass the data
        return HomepageFragment.newInstance(homepages[position])
    }
}
