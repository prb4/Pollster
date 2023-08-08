package com.pollster.ui.submenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pollster.data.Poll
import com.pollster.data.PollQuestion
import com.pollster.support.PollAnswerGrid

val TAG: String = "Pollster - PollQuestions.kt"
@Composable
fun PollQuestionBridge(pollQuestions: List<PollQuestion>){
    Log.d(TAG, "In PollQuestionBridge")
    val context = LocalContext.current
    Intent(context, PollQuestions::class.java).also {
        it.putExtra("EXTRA_POLL_QUESTIONS", ArrayList(pollQuestions))
        context.startActivity(it)
    }
}

class PollQuestions : ComponentActivity() {
    private val TAG: String = "Pollster - PollQuestions.kt"
    //TODO - present multiple questions
    //TODO - keep track of what the user selects

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "In PollQuestions class")
        setContent {
            val pollQuestions: List<PollQuestion> =
                intent.getSerializableExtra("EXTRA_POLL_QUESTIONS") as List<PollQuestion> //TODO - fix
            //for (pollQuestion in pollQuestions) {
            ShowPollQuestion(pollQuestions[0])
            //}
        }
    }

    @Composable
    fun ShowPollQuestion(pollQuestion: PollQuestion) {
        Log.d(TAG, "In ShowPollQuestion()")
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Log.d(TAG, "Sending question: ${pollQuestion.question}")
            PollAnswerGrid(pollQuestion = pollQuestion)
        }
    }
}