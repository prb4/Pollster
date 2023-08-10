package com.pollster.ui.submenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.pollster.data.Poll
import com.pollster.data.PollQuestion
import com.pollster.support.MenuPollGrid

@Composable
fun OpenPollsBridge() {
    val context = LocalContext.current
    val intent : Intent = Intent(context, OpenPolls::class.java)
    context.startActivity(intent)
}

class OpenPolls : ComponentActivity() {
    /*
    Class used to clear the screen and display the available polls that a user can select from
     */
    val TAG: String = "Pollster - OpenPolls.kt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d(TAG, "In OpenPolls class")
            ShowOpenPolls()
            //TODO - show open polls
            //TODO - select open poll
            //TODO - Go to open poll
            //TODO - Return to this screen
        }
    }

    @Composable
    fun ShowOpenPolls() {
        Log.d(TAG, "In ShowOpenPolls")
        var openPolls = getOpenPolls()
        Box(
            modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
        ){
            MenuPollGrid(polls = openPolls)
        }
    }

    fun getOpenPolls(): List<Poll> {
        Log.d(TAG, "In getOpenPolls")
        val poll1Questions = listOf<PollQuestion>(
            PollQuestion(question = "What is your favorite color?",
                        answers = listOf("Red", "Blue", "Green", "Gray")
            ),
            PollQuestion(question = "What day of the week is your favorite?",
                        answers = listOf("Monday", "Tuesday", "Wednesday")
            ),
            PollQuestion(question = "Is it raining today?",
                    answers = listOf("Yes", "No"))
        )

        val poll2Questions = listOf<PollQuestion>(
            PollQuestion(question = "Whats your favorite animal?",
                        answers = listOf("Dog", "Cat", "Ferrer")
            ),
            PollQuestion(question = "What's your favorite season?",
                        answers = listOf("Summer", "Spring", "Fall", "Winter")
            ),
            PollQuestion(question = "Do you choose A or 1?",
                    answers = listOf("A", "1"))
        )
        return listOf(
            Poll(title = "Test Poll 1", uuid = "1", pollQuestions = poll1Questions),
            Poll(title = "Test Poll 2", uuid = "2", pollQuestions = poll2Questions)
        )
    }
}