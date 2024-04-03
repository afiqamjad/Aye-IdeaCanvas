package com.bignerdranch.android.aye_ideacanvas

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val pages: List<Page>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment {
        // Create a new instance of the fragment and pass the data
        return PageFragment.newInstance(pages[position])
    }
}
