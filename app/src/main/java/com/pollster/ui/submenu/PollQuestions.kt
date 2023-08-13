package com.pollster.ui.submenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.unit.dp
import com.pollster.data.PollQuestion
import com.pollster.data.UserAnswer
import com.pollster.data.UserSelection
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
/*
    @Composable
    fun SingleSelectionList(options: List<PollQuestion>, onOptionClicked: (PollQuestion) -> Unit) {
        LazyColumn(){
            items(options[0].answers.size) { index -> SingleSelectionCard(options[index], onOptionClicked)}
        }
    }

    @Composable
    fun SingleSelectionCard(selectionOption: PollQuestion, onOptionClicked: (PollQuestion) -> Unit) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 4.dp)){
            Surface(modifier = Modifier
                .border(1.dp, MaterialTheme.colors.primary, RectangleShape)
                .clickable(true, onClick = {
                    onOptionClicked(selectionOption)
                } ),
                color = if (selectionOption.an){MaterialTheme.colors.primary} else {MaterialTheme.colors.background},
            ){
                Row(modifier = Modifier
                    .padding(16.dp)) {
                    Text(text = selectionOption.option,
                        style = MaterialTheme.typography.body1)
    }
*/
    @Composable
    fun PollQuestionsSequence(pollQuestions: List<PollQuestion>) {
        Log.d(TAG, "In PollQuestionsSequence")

        var currentIndex by remember { mutableStateOf(0) }

        //TODO - should this be rememberSaveable?
        var userSelection by remember { mutableStateOf(UserSelection())}
        var userAnswers by remember { mutableStateOf(
            Array<UserAnswer>(pollQuestions.size) { i -> UserAnswer()}
            )
        }

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
                PollAnswerGrid(pollQuestion = pollQuestions[currentIndex], userSelection = userSelection, onEditUserSelection = {userSelection = it}, userAnswers = userAnswers, currentIndex = currentIndex)
                Log.d(TAG, "User selection: ${userSelection.selection}")

                //TODO - fix this. Having issues accurately tracking the userAnswers. It will update appropriately when the user makes a selection, but will update either the previous or next question with the same (ie: wrong answer)
                for (key in userSelection.selection.keys) {
                    if (key == pollQuestions[currentIndex].question){
                        userAnswers.set(currentIndex, UserAnswer(mapOf(currentIndex to userSelection)))
                    }
                }

                userAnswers.forEach { item ->
                    Log.d(TAG, "Questions: ${item.toString()}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                if (currentIndex == 0){
                    //First question, dont show "last" button option
                    //TODO - Lazy but workable way to pin Next button in right corner
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.weight(.5f))
                    NextButton(currentIndex = currentIndex, size = pollQuestions.size, onIndexChange = { newIndex -> currentIndex = newIndex})

                } else if (currentIndex == pollQuestions.size - 1) {
                    //Last question, dont show "next" button option
                    PreviousButton(currentIndex = currentIndex, size = pollQuestions.size, onIndexChange = { newIndex -> currentIndex = newIndex})
                    Spacer(modifier = Modifier.weight(.5f))
                    FinishButton()


                } else {
                    //Show both options
                    // Button to move to the next or previous image
                    PreviousButton(currentIndex = currentIndex, size = pollQuestions.size, onIndexChange = { newIndex -> currentIndex = newIndex})
                    Spacer(modifier = Modifier.weight(.5f))
                    NextButton(currentIndex = currentIndex, size = pollQuestions.size, onIndexChange = { newIndex -> currentIndex = newIndex})
                }
            }
        }
    }
}
@Composable
fun PreviousButton(currentIndex: Int,
                   size: Int,
                   onIndexChange: (Int) -> Unit)
{
    Button(
        onClick = {
            Log.d(
                TAG,
                "Moving to previous image: ${currentIndex} -> ${currentIndex - 1}"
            )
            onIndexChange((currentIndex - 1) % size)
        },
        //modifier = Modifier.weight(1f)
    ) {
        Text(text = "Previous")
    }
}

@Composable
fun NextButton(currentIndex: Int,
               size: Int,
                onIndexChange: (Int) -> Unit) {
    Button(
        onClick = {
            Log.d(
                TAG,
                "Moving to next image: ${currentIndex} -> ${currentIndex + 1}"
            )
            onIndexChange((currentIndex + 1) % size)
        },
        //modifier = Modifier.weight(1f)
    ) {
        Text(text = "Next")
    }
}

@Composable
fun FinishButton(){
    Button(
        onClick = {
            Log.d(
                TAG,
                "Finishing questions"
                //TODO - confirm all questions have been answered
            )
        },
        //modifier = Modifier.weight(1f)
    //TODO - add weight
    ) {
        Text(text = "Finish")
        //TODO - Add confirmation screen
        //TODo - change color/apperance to distinguish from "Next"
    }
}
/*
class SelectionOption(val option: String, var initialSelectedValue: Boolean){
    var selected by mutableStateOf(initialSelectedValue)

    @Composable
    fun SingleSelectionList(options: List<SelectionOption>, onOptionClicked: (SelectionOption) -> Unit) {
        LazyColumn(){
            items(options) { option -> SingleSelectionCard(option, onOptionClicked)}
        }
    }

    @Composable
    fun SingleSelectionCard(selectionOption: SelectionOption, onOptionClicked: (SelectionOption) -> Unit) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 4.dp)){
            Surface(modifier = Modifier
                .border(1.dp, MaterialTheme.colors.primary, RectangleShape)
                .clickable(true, onClick = {
                    onOptionClicked(selectionOption)
                } ),
                color = if (selectionOption.selected){MaterialTheme.colors.primary} else {MaterialTheme.colors.background},
            ){
              Row(modifier = Modifier
                  .padding(16.dp)) {
                  Text(text = selectionOption.option,
                  style = MaterialTheme.typography.body1)
*/