package com.bignerdranch.android.aye_ideacanvas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.aye_ideacanvas.databinding.FragmentCreateBinding

class CreateFragment : Fragment() {

    private var binding: FragmentCreateBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater, container, false)

        binding?.close?.setOnClickListener {
            Log.d("YOU ARE FUCKED", "hey")
            findNavController().popBackStack()
        }

        return binding?.root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}