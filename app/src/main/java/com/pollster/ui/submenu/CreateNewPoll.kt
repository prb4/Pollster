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
import com.pollster.R
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
            StartNewPoll()
        }
    }
}

@Composable
fun StartNewPoll(){
    var pollQuestions : MutableList<PollQuestion> by remember {
        mutableStateOf(
            mutableListOf<PollQuestion>()
        )
    }
    var choice: Int = -1

    NewPoll(onUserChoice = { it_pollQuestion, it_choice ->
        pollQuestions.add(it_pollQuestion)
        choice = it_choice
        Log.d(TAG, "StartNewPoll: Choice: ${choice}, pollQuestion: ${pollQuestions.toString()}")
    })

    //TODO - how do i get here to continue the work flow?
}

@Composable
fun NewPoll(onUserChoice: (PollQuestion, Int) -> Unit) {
    Log.d(TAG, "in NewPoll")
    var question by remember { mutableStateOf("") }
    var answer1 by remember { mutableStateOf("") }
    var answer2 by remember { mutableStateOf("") }
    var answer3 by remember { mutableStateOf("") }
    var answer4 by remember { mutableStateOf("") }
    var showNextQuestionDialog by remember { mutableStateOf(false)}

    var newPollQuestion: PollQuestion by remember { mutableStateOf(PollQuestion("", listOf(Answer("", false))))}
    //var pollQuestions : List<PollQuestion> = remember { listOf(PollQuestion("", listOf(Answer("", false))))}
    //var pollQuestions : MutableList<PollQuestion> by remember { mutableStateOf(mutableListOf<PollQuestion>())}
    var selectedChoice by remember { mutableStateOf(-1) }

    if (selectedChoice == R.integer.CHOICE_NEXT) {
        Log.d(TAG, "Next was picked")
        question = ""
        answer1 = ""
        answer2 = ""
        answer3 = ""
        answer4 = ""
        onUserChoice(newPollQuestion, R.integer.CHOICE_NEXT)
        selectedChoice = -1

    } else if (selectedChoice == R.integer.CHOICE_FINISH) {
        Log.d(TAG, "Finish was picked")
        onUserChoice(newPollQuestion, R.integer.CHOICE_FINISH)
    }

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
                //Log.d(TAG, "New poll question: ${newPollQuestion}")
            }) {
                Text(text = "Prev Question")
            }
            Button(onClick = { Log.d(TAG, "Clicked on Next button")
                newPollQuestion = buildPollQuestion(question = question, answers = listOf(answer1, answer2, answer3, answer4))
                //Log.d(TAG, "New poll question: ${newPollQuestion}")
                showNextQuestionDialog = true}) {
                Text(text = "Next")
            }
        }

        if (showNextQuestionDialog) {
            CreateNextQuestionDialog(onDismissRequest = {
                showNextQuestionDialog = false
            }){
                selectedChoice = it
                Log.d(TAG, "Choice: ${it}")
            }
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