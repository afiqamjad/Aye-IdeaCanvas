package com.bignerdranch.android.aye_ideacanvas.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.aye_ideacanvas.R
import com.bignerdranch.android.aye_ideacanvas.databinding.FragmentCreateDetailsBinding

class CreateDetailsFragment : Fragment() {

    private var _binding: FragmentCreateDetailsBinding? = null
    private val genres = listOf("Action", "Adventure", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Sci-Fi", "Thriller")
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateDetailsBinding.inflate(inflater, container, false)

        setupSpinners()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.create.setOnClickListener {
            findNavController().popBackStack(R.id.home, false)
            findNavController().navigate(R.id.profile)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSpinners() {
        val genreAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genres)
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val genreOneSpinner: Spinner = binding.genreOneSpinner
        genreOneSpinner.adapter = genreAdapter

        val genreTwoSpinner: Spinner = binding.genreTwoSpinner
        genreTwoSpinner.adapter = genreAdapter
    }

}