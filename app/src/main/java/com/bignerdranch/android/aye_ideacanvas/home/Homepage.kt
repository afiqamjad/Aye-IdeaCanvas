package com.bignerdranch.android.aye_ideacanvas.home

import java.io.Serializable

data class Homepage(
    val username: String,
    val profilePicUrl: String,
    val storyboardUrl: String
) : Serializable
