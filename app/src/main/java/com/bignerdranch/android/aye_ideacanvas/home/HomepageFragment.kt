package com.bignerdranch.android.aye_ideacanvas.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bignerdranch.android.aye_ideacanvas.R
import com.bumptech.glide.Glide

class
HomepageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pfpPic = view.findViewById<ImageView>(R.id.pfpPic)
        val username = view.findViewById<TextView>(R.id.username)
        val storyboard = view.findViewById<ImageView>(R.id.storyboard)

        val data = arguments?.getSerializable("my_data") as? Homepage

        // Load the images with Glide or Picasso
        data?.let {
            Glide.with(this)
                .load(it.profilePicUrl)
                .into(pfpPic)

            Glide.with(this)
                .load(it.storyboardUrl)
                .into(storyboard)

            username.text = it.username
        }
    }

    companion object {
        fun newInstance(data: Homepage): HomepageFragment {
            val fragment = HomepageFragment()

            val args = Bundle()
            args.putSerializable("my_data", data)
            fragment.arguments = args

            return fragment
        }
    }
}
