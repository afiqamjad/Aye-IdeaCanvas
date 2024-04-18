package com.android.aye_ideacanvasv2.ui.create

import android.opengl.Visibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.aye_ideacanvasv2.R
import com.android.aye_ideacanvasv2.model.Screens
import com.android.aye_ideacanvasv2.model.genres
import com.android.aye_ideacanvasv2.ui.ui.theme.AyeIdeaCanvasV2Theme
import com.android.aye_ideacanvasv2.ui.ui.theme.Gray
import com.android.aye_ideacanvasv2.ui.ui.theme.Purple
import com.android.aye_ideacanvasv2.ui.ui.theme.Transparent

@Composable
fun CreateDetails(onNavigate: (Screens) -> Unit) {
    AyeIdeaCanvasV2Theme {
       Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.secondary)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            CreateDetailsTopAppBar()

            Visible()
            Genres()
            InputField(R.string.series_title, R.string.character_limit_50)
            InputField(R.string.tags, R.string.enter_tags)
            InputField(R.string.link, R.string.enter_link)
            Switch()

            Button(
                onClick = {onNavigate(Screens.Profile)},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                shape = MaterialTheme.shapes.medium,
            ) {
                Text(text = stringResource(id = R.string.create_storyboard), color=MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
            }


        }

    }
}

@Composable
fun CreateDetailsTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {  },
            content = {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.close),
                    modifier = Modifier.size(100.dp),
                    colorFilter = ColorFilter.tint(Purple)
                )
            }
        )
    }
}

@Composable
fun MultiToggleButton(
    currentSelection: String,
    toggleStates: List<String>,
    onToggleChange: (String) -> Unit
) {
    val selectedTint = MaterialTheme.colorScheme.onTertiary
    val unselectedTint = MaterialTheme.colorScheme.primary

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.surface))
    ) {
        toggleStates.forEachIndexed { index, toggleState ->
            val isSelected = currentSelection.lowercase() == toggleState.lowercase()
            val backgroundTint = if (isSelected) selectedTint else unselectedTint
            val textColor = MaterialTheme.colorScheme.onSecondary

            if (index != 0) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp),
                    color = MaterialTheme.colorScheme.surface
                )
            }

            Box(
                modifier = Modifier
                    .background(backgroundTint)
                    .padding(vertical = 6.dp)
                    .weight(1f)
                    .toggleable(
                        value = isSelected,
                        enabled = true,
                        onValueChange = { selected ->
                            if (selected) {
                                onToggleChange(toggleState)
                            }
                        }),
                contentAlignment = Alignment.Center
            ) {
                Text(toggleState, color = textColor, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Spinner() {
    val coffeeDrinks = genres.firstOrNull { it.headerText == "Genres" }?.items?.mapNotNull { it }?.toTypedArray()
        ?: emptyArray()
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .height(50.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent
                ),
                textStyle = MaterialTheme.typography.bodyLarge
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            ) {
                coffeeDrinks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}



@Composable
fun Visible() {
    var currentSelection by remember { mutableStateOf("EVERYONE") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.visibility),
            style = MaterialTheme.typography.titleLarge
        )
    }

    MultiToggleButton(
        currentSelection = currentSelection,
        toggleStates = listOf("PRIVATE", "UNLISTED", "EVERYONE"),
        onToggleChange = { newSelection ->
            currentSelection = newSelection
        }
    )
}
@Composable
fun Genres() {
    Row( modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween){

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)){Text(text = stringResource(id = R.string.genre_one), style = MaterialTheme.typography.titleLarge)
            Spinner()}

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)){Text(text = stringResource(id = R.string.genre_two), style = MaterialTheme.typography.titleLarge)
            Spinner()}
    }
}
@Composable
fun InputField(title : Int, placeholder : Int) {
    var input by remember { mutableStateOf("") }

    Text(text = stringResource(id = title), style = MaterialTheme.typography.titleLarge)
    TextField(
        value = input,
        onValueChange = { input = it },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = stringResource(id = placeholder), color=Gray) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent
        ),
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun Switch() {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.mature_rated),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(end=16.dp)
        )
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                checkedTrackColor = MaterialTheme.colorScheme.onTertiary,
                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedTrackColor = MaterialTheme.colorScheme.surface,
            )
        )
    }
}
