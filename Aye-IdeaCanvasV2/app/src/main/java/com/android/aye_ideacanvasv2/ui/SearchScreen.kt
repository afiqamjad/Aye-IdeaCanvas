package com.android.aye_ideacanvasv2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.aye_ideacanvasv2.R
import com.android.aye_ideacanvasv2.model.Genre

@Preview
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    var searchText = ""
    Column(
        modifier = modifier
    ) {
        GenresList(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )
    }
}

@Composable
private fun GenresList(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(Genre.entries.toTypedArray()) { genre ->
            GenreCell(
                genre = genre,
                onClickGenre = {
                    // TODO
                }
            )
        }
    }
}