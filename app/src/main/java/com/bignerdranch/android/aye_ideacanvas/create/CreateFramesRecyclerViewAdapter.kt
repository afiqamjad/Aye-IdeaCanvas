package com.bignerdranch.android.aye_ideacanvas.create

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.aye_ideacanvas.R
import com.bumptech.glide.Glide
import kotlinx.coroutines.selects.select

class CreateFramesRecyclerViewAdapter(
    context: Context,
    private var mData: List<String>
) : RecyclerView.Adapter<CreateFramesRecyclerViewAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var mClickListener: ItemClickListener
    private var selectedItemPosition: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.frame_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(mData[position])
            .into(holder.button)

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
            val prevSelected = selectedItemPosition
            selectedItemPosition = adapterPosition
            notifyItemChanged(prevSelected)
            notifyItemChanged(adapterPosition)
            mClickListener.onItemClick(view, adapterPosition)
        }
    }

    fun getItem(id: Int): String {
        return mData[id]
    }

    fun setClickListener(itemClickListener: CreateFragment) {
        mClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

}
