package com.android.aye_ideacanvasv2.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.aye_ideacanvasv2.R
import com.android.aye_ideacanvasv2.ui.ui.theme.White


@Composable
fun Profile() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            BannerProfile()
        }
        item {
            UserName()
        }
        item {
            UserDescription()
        }

        item {
            ProfilePost()
        }
        item {
            ProfilePost()
        }
        item {
            ProfilePost()
        }
        item {
            ProfilePost()
        }
    }
}

@Composable
fun BannerProfile(){
    Box (modifier = Modifier.padding(bottom = 40.dp)){
        Image(
            painter = painterResource(id = R.drawable.placeholder_image),
            "Profile Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 150.dp)
        )

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            shape = CircleShape,
            modifier = Modifier
                .size(100.dp)
                .align(BottomStart)
                .offset(30.dp, 40.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            content = {
                Image(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
    }
}
@Composable
fun UserName() {
    Text(
        text = "Tom Hanks",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 33.dp, top = 17.dp)
    )
}

@Composable
fun UserDescription() {
    Text(
        text = "I am so amazing.",
        fontSize = 16.sp,
        modifier = Modifier.padding(start = 35.dp, top = 3.dp, bottom = 64.dp)
    )
}

@Composable
fun ProfilePost() {
    Image(
        painter = painterResource(id = R.drawable.placeholder_image),
        contentDescription = stringResource(id = R.string.profile_picture),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
    )
}

@Preview
@Composable
fun PreviewProfile() {
    Profile()
}