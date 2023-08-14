package com.pollster.ui.submenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.pollster.data.Answer
import com.pollster.data.PollQuestion

private val TAG: String = "Pollster - CreateNewPoll.kt"
@Composable
fun CreateNewPollBridge() {
    val context = LocalContext.current
    val intent: Intent = Intent(context, CreateNewPoll::class.java)
    context.startActivity(intent)
}

class CreateNewPoll : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewPoll()
        }
    }
}

@Composable
fun NewPoll() {
    Log.d(TAG, "in CreateNewPoll")
    var question by remember { mutableStateOf("") }
    var answer1 by remember { mutableStateOf("") }
    var answer2 by remember { mutableStateOf("") }
    var answer3 by remember { mutableStateOf("") }
    var answer4 by remember { mutableStateOf("") }
    var showNextQuestionDialog by remember { mutableStateOf(false)}
    var pollQuestions: List<PollQuestion> by remember {
        mutableStateOf(
            listOf(
                PollQuestion(
                    "", listOf(
                        Answer(
                            "", false
                        )
                    )
                )
            )
        )
    }
    var newPollQuestion: PollQuestion = PollQuestion("", listOf(Answer("", false)))

    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            value = question,
            onValueChange = { question = it },
            label = { Text("Question") }
        )
        Spacer(modifier = Modifier.weight(0.5f))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            value = answer1,
            onValueChange = { answer1 = it },
            label = { Text("Answer 1") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            value = answer2,
            onValueChange = { answer2 = it },
            label = { Text("Answer 2") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            value = answer3,
            onValueChange = { answer3 = it },
            label = { Text("Answer 3") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            value = answer4,
            onValueChange = { answer4 = it },
            label = { Text("Answer 4") }
        )
        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(onClick = { Log.d(TAG, "Clicked on Prev button")
                newPollQuestion = buildPollQuestion(question = question, answers = listOf(answer1, answer2, answer3, answer4))
            }) {
                Text(text = "Prev Question")
            }
            Button(onClick = { Log.d(TAG, "Clicked on Next button")
                newPollQuestion = buildPollQuestion(question = question, answers = listOf(answer1, answer2, answer3, answer4))
                showNextQuestionDialog = true}) {
                Text(text = "Next")
            }
        }

        if (showNextQuestionDialog) {
            CreateNextQuestionDialog(onDismissRequest = {
                showNextQuestionDialog = false
            })
            
        }
    }
}

fun buildPollQuestion(question: String, answers: List<String>) : PollQuestion {
    var pollQuestion : PollQuestion = PollQuestion("", List(answers.size){Answer("", false)})
    var pollAnswers : MutableList<Answer> = List(answers.size){Answer("", false)}.toMutableList()

    pollQuestion.question = question

    for (index in answers.indices) {
        pollAnswers[index] = Answer(answers[index], false)
    }

    pollQuestion.answers = pollAnswers
    return pollQuestion
}