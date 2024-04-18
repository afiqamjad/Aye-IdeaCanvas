package com.android.aye_ideacanvasv2.model

import java.io.Serializable

data class User(
    val username: String,
    val profilePicUrl: String,
) : Serializable