package com.android.aye_ideacanvasv2.model

import java.io.Serializable

data class Post(
    val username: String,
    val profilePicUrl: String,
    val storyboardUrl: String
) : Serializable