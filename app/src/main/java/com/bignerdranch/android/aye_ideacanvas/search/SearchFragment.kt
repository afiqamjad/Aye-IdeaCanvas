package com.bignerdranch.android.aye_ideacanvas.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bignerdranch.android.aye_ideacanvas.R
import com.bignerdranch.android.aye_ideacanvas.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val searchList = listOf(
        "Genres" to listOf("Adventure", "Mystery", "Horror"),
        "Tags" to listOf("Relationship(s)", "Angst", "Mature Content"),
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = binding.searchBar

        val expandableListView: ExpandableListView = view.findViewById(R.id.expandableListView)

        val adapter = GenreAdapter(requireContext(), searchList)
        expandableListView.setAdapter(adapter)

        searchView.setOnClickListener {
            searchView.isIconified = false
        }

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class GenreAdapter(private val context: Context, private val searchList: List<Pair<String, List<String>>>) : BaseExpandableListAdapter() {
        override fun getGroupCount(): Int {
            return searchList.size
        }
        override fun getChildrenCount(groupPosition: Int): Int {
            return searchList[groupPosition].second.size
        }
        override fun getGroup(groupPosition: Int): Any {
            return searchList[groupPosition].first
        }
        override fun getChild(groupPosition: Int, childPosition: Int): Any {
            return searchList[groupPosition].second[childPosition]
        }
        override fun getGroupId(groupPosition: Int): Long {
            return groupPosition.toLong()
        }
        override fun getChildId(groupPosition: Int, childPosition: Int): Long {
            return childPosition.toLong()
        }
        override fun hasStableIds(): Boolean {
            return true
        }
        override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
            val textView = view.findViewById<TextView>(android.R.id.text1)
            textView.text = getGroup(groupPosition) as String
            return view
        }
        override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
            val textView = view.findViewById<TextView>(android.R.id.text1)
            textView.text = getChild(groupPosition, childPosition) as String
            return view
        }
        override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
            return true
        }
    }

}