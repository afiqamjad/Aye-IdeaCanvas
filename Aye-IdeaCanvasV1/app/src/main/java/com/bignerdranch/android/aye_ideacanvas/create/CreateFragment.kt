package com.bignerdranch.android.aye_ideacanvas.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.aye_ideacanvas.R
import com.bignerdranch.android.aye_ideacanvas.databinding.FragmentCreateBinding

class CreateFragment : Fragment(), CreateFramesRecyclerViewAdapter.ItemClickListener {

    private var _binding: FragmentCreateBinding? = null
    private lateinit var adapter: CreateFramesRecyclerViewAdapter
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)

        val frameData = listOf("https://th-thumbnailer.cdn-si-edu.com/aMjaS_ajJ7-4Piaaebj7PSqiJUc=/1000x750/filters:no_upscale()/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/34/d0/34d02897-7dde-4ed6-9a51-f4fe4733b522/crying_baby.jpg", "https://th-thumbnailer.cdn-si-edu.com/aMjaS_ajJ7-4Piaaebj7PSqiJUc=/1000x750/filters:no_upscale()/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/34/d0/34d02897-7dde-4ed6-9a51-f4fe4733b522/crying_baby.jpg")
        val recyclerView: RecyclerView = binding.frames
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = CreateFramesRecyclerViewAdapter(requireContext(), frameData)
        adapter.setClickListener(this)
        recyclerView.adapter = adapter

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.close.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_create_to_createDetails)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(view: View?, position: Int) {

    }
}