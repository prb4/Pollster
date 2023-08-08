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

@Composable
fun PollQuestionBridge(pollQuestion: PollQuestion){
    val context = LocalContext.current
    val intent : Intent = Intent(context, PollQuestions::class.java)
    intent.putExtra("pollQuestion", pollQuestion)
    context.startActivity(intent)
}

class PollQuestions : ComponentActivity() {
    private val TAG: String = "Pollster - PollQuestions.kt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pollQuestion: PollQuestion =
                intent.getSerializableExtra("pollQuestion") as PollQuestion //TODO - fix
            ShowPollQuestion(pollQuestion)

        }
    }

    @Composable
    fun ShowPollQuestion(pollQuestion: PollQuestion) {
        Log.d(TAG, "In ShowPollQuestion()")
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ){
            PollAnswerGrid(pollQuestion = pollQuestion)
        }
    }
}