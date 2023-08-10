package com.pollster.ui.submenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
    //TODO - keep track of what the user selects

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "In PollQuestions class")
        setContent {
            val pollQuestions: List<PollQuestion> =
                intent.getSerializableExtra("EXTRA_POLL_QUESTIONS") as List<PollQuestion> //TODO - fix
            PollQuestionsSequence(pollQuestions = pollQuestions)
        }
    }

    @Composable
    fun PollQuestionsSequence(pollQuestions: List<PollQuestion>) {
        Log.d(TAG, "In PollQuestionsSequence")

        var currentIndex by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the current image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .weight(1f)
            ) {
                Log.d(TAG, "Displaying question: ${pollQuestions[currentIndex].question}")
                PollAnswerGrid(pollQuestion = pollQuestions[currentIndex])
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Button to move to the next or previous image
                Button(
                    onClick = {
                        Log.d(TAG, "Moving to previous image: ${currentIndex} -> ${currentIndex - 1}")
                        currentIndex = (currentIndex - 1) % pollQuestions.size
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Previous image")
                }

                Spacer(modifier = Modifier.weight(.5f))

                Button(
                    onClick = {
                        Log.d(TAG, "Moving to next image: ${currentIndex} -> ${currentIndex + 1}")
                        currentIndex = (currentIndex + 1) % pollQuestions.size

                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Next image")
                }
            }
        }
    }
}