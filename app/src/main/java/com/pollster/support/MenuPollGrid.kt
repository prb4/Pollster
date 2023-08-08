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
import com.pollster.data.Poll
import com.pollster.data.PollQuestion
import com.pollster.ui.submenu.PollQuestionBridge
import com.pollster.ui.submenu.PollQuestions

private val TAG: String = "Pollster - MenuPollGrid.kt"

@Composable
fun MenuPollGrid(polls: List<Poll>) {
    Log.d(TAG, "In ListPollsGrid()")
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(polls.size) { index ->
            MenuPoll(poll = polls[index])
        }
    }
}

@Composable
fun MenuPoll(poll: Poll) {
    //The display details of the card
    Log.d(TAG, "In Poll()")
    var callbackFlag by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp) //TODO - make this 25% of the screen height
            .clickable(onClick = {
                Log.d(TAG, "Clicked on ${poll.title}")
                callbackFlag = !callbackFlag
            }),
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
                text = poll.title.uppercase(),
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            if (callbackFlag) {
                Log.d(TAG, "PollAnswer callback flag: ")
                //TODO - Send multiple questions
                PollQuestionBridge(poll.pollQuestions)
            }

        }
    }
}