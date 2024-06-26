package com.android.aye_ideacanvasv2.ui.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.android.aye_ideacanvasv2.R
import com.android.aye_ideacanvasv2.model.Post
import com.android.aye_ideacanvasv2.ui.ui.theme.HeartPink
import com.android.aye_ideacanvasv2.ui.ui.theme.White

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home() {
    val data = listOf(
        Post("Tom Hanks", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRhvG2pdEygKaC6fsp7fCHvulcfTMNdA2Q4Djv4j2JGElwOIyFb", "https://m.media-amazon.com/images/M/MV5BMmExZDc4NjEtZjY1ZS00OWU5LWExZGYtYTc4NDM1ZmRhMDZhXkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_.jpg"),
        Post("Cristiano Ronaldo", "data:image/jpeg;base64,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", "https://i.redd.it/e9pea1tthzc51.jpg"),
    )

    VerticalPager(state = rememberPagerState(pageCount = { 2 })) { verticalPage ->
        FeedPost(data[verticalPage].storyboardUrl)
    }
}

@Composable
fun FeedPost(storyBoard: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PostImage(storyBoard)

        Card(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.TopStart)
                .offset(25.dp, 25.dp),
            shape = CircleShape
        ) {
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Picture",
                modifier = Modifier.fillMaxSize()
            )
        }

        var isFavorite by remember {
            mutableStateOf(false)
        }

        Icon(
            imageVector =  Icons.Filled.Favorite,
            tint = if(isFavorite) { HeartPink } else { White },
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.TopEnd)
                .offset((-75).dp, 30.dp)
                .bounceClickable(
                    minScale = 0.5f,
                    onAnimationFinished = {
                        isFavorite = !isFavorite
                    }
                ) {
                },
        )

        IconButton(
            onClick = { },
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.TopEnd)
                .offset((-20).dp, 34.dp)) {
            Icon(
                painterResource(id = R.drawable.baseline_comment_24),
                "contentDescription",
                tint = White,
                modifier = Modifier
                    .fillMaxSize()
                )
        }
    }
}

@Composable
fun PostImage(storyBoard : String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(storyBoard)
            .crossfade(true)
            .build(),
        contentDescription = "StoryBoard Post",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}

fun Modifier.bounceClickable(
    minScale: Float = 0.5f,
    onAnimationFinished: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) = composed {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) minScale else 1f,
        label = ""
    ) {
        if(isPressed) {
            isPressed = false
            onAnimationFinished?.invoke()
        }
    }

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable {
            isPressed = true
            onClick?.invoke()
        }
}


@Preview
@Composable
fun PreviewHome() {
    Home()
}