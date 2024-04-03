package com.bignerdranch.android.aye_ideacanvas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class
PageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pfpPic = view.findViewById<ImageView>(R.id.pfpPic)
        val username = view.findViewById<TextView>(R.id.username)
        val storyboard = view.findViewById<ImageView>(R.id.storyboard)

        val data = arguments?.getSerializable("my_data") as? Page

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
        fun newInstance(data: Page): PageFragment {
            val fragment = PageFragment()

            val args = Bundle()
            args.putSerializable("my_data", data)
            fragment.arguments = args

            return fragment
        }
    }
}
