package com.bignerdranch.android.aye_ideacanvas.create

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.aye_ideacanvas.R

class CreateFramesRecyclerViewAdapter(
    context: Context,
    private var mData: MutableList<String>
) : RecyclerView.Adapter<CreateFramesRecyclerViewAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var mClickListener: ItemClickListener
    private var selectedItemPosition: Int = 0

    private val viewTypeButton = 0
    private val viewTypeAdd = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view : View = if (viewType == viewTypeAdd) {
            mInflater.inflate(R.layout.create_frame_add, parent, false)
        } else {
            mInflater.inflate(R.layout.create_frame_item, parent, false)
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.button.isSelected = position == selectedItemPosition
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var button: ImageButton = itemView.findViewById(R.id.frame)

        init {
            button.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (itemViewType == viewTypeAdd) {
                val newFrameTemplateView = itemView.findViewById<View>(R.id.newFrameTemplate)
                val newFrameTemplate = itemView.findViewById<View>(R.id.emptyFrameButton)

                if (newFrameTemplateView.visibility == View.VISIBLE) {
                    newFrameTemplateView.visibility = View.GONE
                    newFrameTemplate.setOnClickListener(null)
                } else {
                    newFrameTemplateView.visibility = View.VISIBLE

                    newFrameTemplate.setOnClickListener { _ ->
                        mData.add(itemCount - 1,"New Item")
                        notifyItemInserted(itemCount)
                    }

                }
            } else {
                val prevSelected = selectedItemPosition
                selectedItemPosition = adapterPosition
                notifyItemChanged(prevSelected)
                notifyItemChanged(adapterPosition)
            }

            mClickListener.onItemClick(button, adapterPosition)
        }
    }

    fun getItem(id: Int): String {
        return mData[id]
    }

    fun setClickListener(itemClickListener: CreateFragment) {
        mClickListener = itemClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < itemCount - 1) {
            viewTypeButton
        } else {
            viewTypeAdd
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

}
