package com.pollster.support

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
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
import com.pollster.data.UserAnswer
import com.pollster.data.UserSelection

private val TAG: String = "Pollster - PollAnswer.kt"

@Composable
fun PollAnswerGrid(pollQuestion: PollQuestion, userSelection: UserSelection, onEditUserSelection: (UserSelection) -> Unit, userAnswers: Array<UserAnswer>, currentIndex: Int) {
    Log.d(TAG, "In PollAnswerGrid(): ${userAnswers[currentIndex].toString()}")

    //TODO - Improve layout of screen
    Column {
        Text(text = pollQuestion.question, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            pollQuestion.answers.forEach { item ->
                var isSelected = false
                //TODO - this access / data format is ugly
                var selectedItem = userAnswers[currentIndex].userAnswer.get(currentIndex)?.selection?.get(pollQuestion.question)
                if (item.answer == selectedItem) {
                    isSelected = true
                }

                item {
                    Card(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            //.height(150.dp) //TODO - make this 25% of the screen height
                            .fillMaxHeight()
                            .selectable(
                                selected = item.isSelected,
                                onClick = {
                                    onEditUserSelection(userSelection.withSelection(pollQuestion.question, item.answer))
                                }
                            ),
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = if (isSelected) Color.Yellow else Color.LightGray,
                        elevation = 5.dp
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = item.answer.uppercase(),
                                modifier = Modifier.padding(8.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
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