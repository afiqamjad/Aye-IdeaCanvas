package com.bignerdranch.android.aye_ideacanvas.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.aye_ideacanvas.R

class HomeFragment : Fragment() {
    private lateinit var viewModel: PageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        viewModel = ViewModelProvider(this)[PageViewModel::class.java]

        viewModel.pageData.observe(viewLifecycleOwner) { data ->
            viewPager.adapter = PageAdapter(childFragmentManager, lifecycle, data)
        }
        return view
    }

}

