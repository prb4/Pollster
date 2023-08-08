package com.pollster.support

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pollster.data.ImageCard

private val TAG: String = "Pollster - ImageCardGrid.kt"

@Composable
fun ImageCardGrid(imageCardList: List<ImageCard>) {
    Log.d(TAG, "In ImageCardGrid()")
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(imageCardList.size) { index ->
            ImageCard(imageCard = imageCardList[index])
        }
    }
}

@Composable
fun ImageCard(imageCard: ImageCard) {
    //The display details of the card
    Log.d(TAG, "In ImageCard(): ${imageCard.title}")
    var callbackFlag by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp) //TODO - make this 25% of the screen height
            .clickable(onClick = {
                Log.d(TAG, "Clicked on ${imageCard.title}")
                callbackFlag = !callbackFlag }),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.LightGray,
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = imageCard.title.uppercase(),
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            if (callbackFlag) {
                Log.d(TAG, "Calling callback function for ${imageCard.title}")
                imageCard.onClickCard()
            }

        }
    }
}