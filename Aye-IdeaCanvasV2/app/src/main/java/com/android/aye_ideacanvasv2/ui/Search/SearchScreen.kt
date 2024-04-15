package com.android.aye_ideacanvasv2.ui.Search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
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