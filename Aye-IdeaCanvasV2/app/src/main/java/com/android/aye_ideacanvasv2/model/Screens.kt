package com.android.aye_ideacanvasv2.model
import com.android.aye_ideacanvasv2.R

sealed class Screens(val route: String, val icon: Int, val label: Int) {
    data object Home :
        Screens("home", R.drawable.baseline_home_24, R.string.home)

    data object Search :
        Screens("search", R.drawable.baseline_search_24, R.string.search)

    data object Create :
        Screens("create", R.drawable.baseline_add_box_24, R.string.create)
    data object CreateDetails :
        Screens("createDetails", R.drawable.baseline_add_box_24, R.string.create_details)

    data object Notifications :
        Screens("notifications", R.drawable.baseline_notifications_24, R.string.notifications)

    data object Profile :
        Screens("profile", R.drawable.baseline_person_outline_24, R.string.profile)
}