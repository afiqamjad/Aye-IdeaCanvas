package com.bignerdranch.android.aye_ideacanvas

import java.io.Serializable

data class Page(
    val username: String,
    val profilePicUrl: String,
    val storyboardUrl: String
) : Serializable

