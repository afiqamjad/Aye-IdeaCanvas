package com.bignerdranch.android.aye_ideacanvas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    // LiveData allows your Activity or Fragment to observe changes to the data
    private val homeData: LiveData<List<Home>> = MutableLiveData()

    init {
        loadData()
    }

    private fun loadData() {
        // Fetch or generate your data here. This is just a placeholder.
        val data = listOf(
            Home("User1", "https://example.com/profile1.jpg", "https://example.com/storyboard1.jpg"),
            Home("User2", "https://example.com/profile2.jpg", "https://example.com/storyboard2.jpg"),
            // Add more data...
        )

        // Update the LiveData with the new data
        (homeData as MutableLiveData).value = data
    }
}
