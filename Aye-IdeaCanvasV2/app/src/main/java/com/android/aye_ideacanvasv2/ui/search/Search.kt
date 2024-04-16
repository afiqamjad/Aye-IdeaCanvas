package com.android.aye_ideacanvasv2.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.aye_ideacanvasv2.IdeaCanvasDB
import com.android.aye_ideacanvasv2.model.genres
import com.android.aye_ideacanvasv2.model.tags
import kotlinx.coroutines.launch

@Preview
@Composable
fun Search() {
    var isLoading by remember { mutableStateOf(true) }
    var fetchedData by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        launch {
            fetchedData = IdeaCanvasDB.fetchData("name", "genre_table", "7cf61670-e8f7-400f-9aa4-7f39517d72b0")
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            SearchBar(fetchedData)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ExpandableList(headerText = "Genres") {
                GenreGrid()
            }
            ExpandableList(headerText = "Tags") {
                TagGrid()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(fetchedData: String?) {
    var searchText by remember { mutableStateOf("") }

    CenterAlignedTopAppBar(
        title = {
            if (fetchedData != null) {
                Text(fetchedData)
            } else {
                Text("Loading...") // Display loading text while data is fetched
            }
        }
        /**title = {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search") },
            )
        }*/
    )
}

@Composable
private fun GenreGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres.flatMap { it.items }.toList()) { item ->
            if (item != null) {
                GenreItem(text = item)
            }
        }
    }
}

@Composable
private fun GenreItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Checkbox(checked = false, onCheckedChange = {})
        Text(text = text)
    }
}

@Composable
private fun TagGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tags.flatMap { it.items }.toList()) { item ->
            if (item != null) {
                TagItem(text = item)
            }
        }
    }
}

@Composable
private fun TagItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Checkbox(checked = false, onCheckedChange = {})
        Text(text = text)
    }
}

@Composable
private fun ExpandableList(headerText: String, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(true) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = headerText, style = MaterialTheme.typography.headlineSmall)
            if (expanded) {
                content()
            }
        }
    }
}