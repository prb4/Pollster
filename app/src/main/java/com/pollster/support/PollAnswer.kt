package com.pollster.support

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pollster.data.PollQuestion

private val TAG: String = "Pollster - PollAnswer.kt"

@Composable
fun PollAnswerGrid(pollQuestion: PollQuestion) {
    Log.d(TAG, "In PollAnswerGrid()")
    //TODO - Improve layout of screen
    Column {
        Text(text = pollQuestion.question, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            items(pollQuestion.answers.size) { index ->
                PollAnswer(pollAnswer = pollQuestion.answers[index])
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SubmitButton()



    }
}

@Composable
fun SubmitButton(){
    Log.d(TAG, "In SubmitButton")
    Button(
        onClick = { Log.d(TAG, "Next Button selected") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
    ) {
        Text(text = "Next", fontSize = 20.sp, color = Color.Red)
    }
}

@Composable
fun PollAnswer(pollAnswer: String) {
    //The display details of the card
    Log.d(TAG, "In PollAnswer()")
    var callbackFlag by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp) //TODO - make this 25% of the screen height
            .clickable(onClick = {
                Log.d(TAG, "Clicked on ${pollAnswer}")
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
                text = pollAnswer.uppercase(),
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            if (callbackFlag) {
                Log.d(TAG, "Calling callback function for pollanswer: ${pollAnswer}")
                //showPoll(poll.pollQuestions)
            }

        }
    }
}