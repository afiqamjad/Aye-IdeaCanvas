package com.bignerdranch.android.aye_ideacanvas.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bignerdranch.android.aye_ideacanvas.R
import com.bignerdranch.android.aye_ideacanvas.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.notificationsNavContainer)

        showActivityFragment()

        binding.notificationsToggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.activity -> showActivityFragment()
                    R.id.messages -> showMessagesFragment()
                }
            }
        }
    }

    private fun showActivityFragment() {
        val fragment = NotificationsActivityFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.notificationsNavContainer, fragment)
            .commit()
    }

    private fun showMessagesFragment() {
        val fragment = NotificationsMessagesFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.notificationsNavContainer, fragment)
            .commit()
    }

}
