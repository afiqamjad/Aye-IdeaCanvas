package com.android.aye_ideacanvasv2.model

data class ItemData(val headerText: String?, val items: List<String?>)

val genres = listOf(
    ItemData(
        headerText = "Genres",
        items = listOf(
            "Romance",
            "Science Fiction",
            "Historical Fiction",
            "Horror",
            "Dystopian",
            "Crime Fiction",
            "Fantasy",
            "Mystery"
        )
    )
)