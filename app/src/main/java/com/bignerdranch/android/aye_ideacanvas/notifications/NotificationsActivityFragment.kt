package com.bignerdranch.android.aye_ideacanvas.notifications

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.aye_ideacanvas.R

class NotificationsActivityFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("OH YEAHH", "motherfuckers")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("OH YEAHH", "motherfuckers")
        return inflater.inflate(R.layout.fragment_notifications_activity, container, false)
    }
}