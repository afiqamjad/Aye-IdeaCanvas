package com.android.aye_ideacanvasv2.model

import androidx.annotation.StringRes
import com.android.aye_ideacanvasv2.R
enum class Genre(
    @StringRes val genreName: Int
) {
    Romance(
        genreName = R.string.genre_romance
    ),
    ScienceFiction(
        genreName = R.string.genre_science_fiction
    ),
    HistoricalFiction(
        genreName = R.string.genre_historical_fiction
    ),
    Horror(
        genreName = R.string.genre_horror
    ),
    Dystopian(
        genreName = R.string.genre_dystopian
    ),
    CrimeFiction(
        genreName = R.string.genre_crime_fiction
    ),
    Fantasy(
        genreName = R.string.genre_fantasy
    ),
    Mystery(
        genreName = R.string.genre_mystery
    )
}