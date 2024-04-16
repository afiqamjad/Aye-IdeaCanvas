package com.android.aye_ideacanvasv2.ui.Search

import android.content.Context
import android.os.Handler
import android.util.Log
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

data class ItemData(val headerText: String, val items: List<String>)

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

val tags = listOf(
    ItemData(
        headerText = "Tags",
        items = listOf(
            "Drama",
            "Art",
            "Education",
            "Family",
            "Friendship",
            "Love",
            "Medical",
            "Action",
            "Holidays",
            "Sports",
            "Team",
            "War"
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Search() {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            SearchBar()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { fetchData("name", "genre_table") }) {
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
private fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    CenterAlignedTopAppBar(
        title = {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search") },
            )
        }
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
            GenreItem(text = item)
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
            TagItem(text = item)
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

fun insertData(data: String, whichTable: String) {
    Thread {
        try {
            Class.forName("org.postgresql.Driver")

            val conn: Connection = DriverManager.getConnection(
                "jdbc:postgresql://aye-ideacanvas-14031.7tt.aws-us-east-1.cockroachlabs.cloud:26257/defaultdb?sslmode=require",
                "elliot",
                "<YOUR_DB_PASSWORD>"
            )

            val statement = conn.prepareStatement("SELECT $data FROM $whichTable ORDER BY random() LIMIT 1")
            val resultSet = statement.executeQuery()

            if (resultSet.next()) {
                val randomItem = resultSet.getString("name")

                Log.d("Database Action", "Random Item: $randomItem")
            } else {
                Log.d("Database Action", "No data found")
            }

            conn.close()
        }  catch (e: SQLException) {
            e.printStackTrace()
            Log.d("Database Action", "Failed to insert data")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            Log.d("Database Action", "Failed to load JDBC driver")
        }
    }.start()
}

fun fetchData(name: String, whichTable: String) {
    Thread {
        try {
            Class.forName("org.postgresql.Driver")

            val conn: Connection = DriverManager.getConnection(
                "jdbc:postgresql://aye-ideacanvas-14031.7tt.aws-us-east-1.cockroachlabs.cloud:26257/defaultdb?sslmode=require",
                "elliot",
                "<YOUR_DB_PASSWORD>"
            )

            val statement = conn.prepareStatement("SELECT name FROM genre_table ORDER BY random() LIMIT 1")
            val resultSet = statement.executeQuery()

            if (resultSet.next()) {
                val randomItem = resultSet.getString("name")
                Log.d("Database Action", "Random Item: $randomItem")
            } else {
                Log.d("Database Action", "No data found")
            }

            conn.close()
        } catch (e: SQLException) {
            e.printStackTrace()
            Log.d("Database Action", "Failed to fetch data")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            Log.d("Database Action", "Failed to load JDBC driver")
        }
    }.start()
}
