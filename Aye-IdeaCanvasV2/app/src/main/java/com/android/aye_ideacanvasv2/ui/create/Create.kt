package com.android.aye_ideacanvasv2.ui.create

import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.os.Environment
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.android.aye_ideacanvasv2.R
import com.android.aye_ideacanvasv2.model.Screens
import com.android.aye_ideacanvasv2.ui.ui.theme.Gray
import com.android.aye_ideacanvasv2.ui.ui.theme.LightGray
import com.android.aye_ideacanvasv2.ui.ui.theme.Purple
import com.android.aye_ideacanvasv2.ui.ui.theme.Transparent
import com.android.aye_ideacanvasv2.ui.ui.theme.White
import java.io.File
import java.io.FileOutputStream

@Composable
fun Create(onNavigate: (Screens) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {

            CreateTopAppBar(onNavigate)

            TransformableView(
                modifier = Modifier
                    .clipToBounds()
                    .background(Gray)
                    .weight(0.7f)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .aspectRatio(9f / 16f)
                        .background(White)
                ) {

                    StandardTemplate()

                }
            }

            Column(
                modifier = Modifier
                    .weight(0.2f)
                    .padding(8.dp, 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_music_note_24),
                    contentDescription = "Add Music"
                )
                FrameButtons(1)

            }

        }
    }
}

@Composable
fun CreateTopAppBar(onNavigate: (Screens) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {onNavigate(Screens.Home)},
            content = {
                Image(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier.size(100.dp),
                    colorFilter = ColorFilter.tint(Purple)
                )
            }
        )

        Button(
            onClick = {onNavigate(Screens.CreateDetails) },
            modifier = Modifier.width(100.dp),
            colors = ButtonColors(contentColor = Purple,
                containerColor = Transparent,
                disabledContentColor = Transparent,
                disabledContainerColor = Transparent),
            content = {
                Text(
                    text = "Next",
                    color = Purple,
                    fontSize = 24.sp
                )
            }
        )
    }
}
@Composable
fun TransformableView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scale = remember { mutableFloatStateOf(1f) }
    val rotationState = remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, rotation ->
                    scale.floatValue *= zoom
                    rotationState.floatValue += rotation
                }
            }

    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = maxOf(.5f, minOf(3f, scale.floatValue)),
                    scaleY = maxOf(.5f, minOf(3f, scale.floatValue)),
                    rotationZ = rotationState.floatValue
                )
        ) {
            content()
        }
    }
}

@Composable
fun FrameButtons(num : Int){
    var lastPressedIndex by remember { mutableIntStateOf(0) }
    var itemCount by remember { mutableIntStateOf(num) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        items(itemCount) { index ->
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .aspectRatio(9f / 16f)
                    .padding(horizontal = 2.dp, vertical = 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        width = 1.25.dp,
                        color = if (index == lastPressedIndex) Purple else Gray
                    ),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { lastPressedIndex = index}
                            .background(White)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                    }
                }
            }
        }
        item {

            Box(
                modifier = Modifier
                    .width(60.dp)
                    .aspectRatio(9f / 16f)
                    .padding(horizontal = 2.dp, vertical = 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        width = 1.25.dp,
                        color = Gray
                    ),
                    color = Transparent // Transparent background
                ) {
                    IconButton(
                        onClick = { itemCount++},
                        modifier = Modifier
                            .fillMaxSize(),
                        content = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Icon",
                            )
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun StandardTemplate() {
    Box(
        modifier = Modifier
            .aspectRatio(9f / 16f)
            .background(White)
    )  {
        var storyboardTitle by remember { mutableStateOf("") }
        var bodyText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            OutlinedTextField(
                value = storyboardTitle,
                onValueChange = { storyboardTitle = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                singleLine = true,
                placeholder = { Text(text = stringResource(id = R.string.storyboard_title), fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Transparent,
                    unfocusedBorderColor = Transparent
                )
            )

            Spacer(modifier = Modifier.height(0.dp))

            OutlinedTextField(
                value = bodyText,
                onValueChange = { bodyText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp, max = 475.dp),
                textStyle = TextStyle(fontSize = 12.sp),
                placeholder = { Text(text = stringResource(id = R.string.body_text_optional)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Transparent,
                    unfocusedBorderColor = Transparent
                ),
                maxLines = Int.MAX_VALUE
            )
        }
    }
}

