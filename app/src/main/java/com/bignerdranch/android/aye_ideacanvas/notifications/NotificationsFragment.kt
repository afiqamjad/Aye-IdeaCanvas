package com.bignerdranch.android.aye_ideacanvas.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.aye_ideacanvas.R
import com.bignerdranch.android.aye_ideacanvas.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nestedFragmentManager = childFragmentManager

        binding.notificationsToggleGroup.check(R.id.activity)
        nestedFragmentManager.beginTransaction().apply {
            replace(R.id.notificationsNavContainer, NotificationsActivityFragment())
            commit()
        }

        binding.notificationsToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.messages -> {
                        nestedFragmentManager.beginTransaction().apply {
                            replace(R.id.notificationsNavContainer, NotificationsMessagesFragment())
                            commit()
                        }
                    }
                    R.id.activity -> {
                        nestedFragmentManager.beginTransaction().apply {
                            replace(R.id.notificationsNavContainer, NotificationsActivityFragment())
                            commit()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
