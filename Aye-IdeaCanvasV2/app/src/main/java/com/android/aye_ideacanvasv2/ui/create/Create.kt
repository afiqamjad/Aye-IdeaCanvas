package com.android.aye_ideacanvasv2.ui.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.aye_ideacanvasv2.R
import com.android.aye_ideacanvasv2.model.Screens
import com.android.aye_ideacanvasv2.ui.ui.theme.Gray
import com.android.aye_ideacanvasv2.ui.ui.theme.LightGray
import com.android.aye_ideacanvasv2.ui.ui.theme.Purple
import com.android.aye_ideacanvasv2.ui.ui.theme.Transparent
import com.android.aye_ideacanvasv2.ui.ui.theme.White

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
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.2f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_music_note_24),
                    contentDescription = "Add Music",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                )
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