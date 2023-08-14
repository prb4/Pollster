package com.pollster.ui.submenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pollster.ui.submenu.ui.theme.PollsterTheme

private val TAG: String = "Pollster - CreatePoll.kt"

@Composable
fun CreatePollBridge() {
    val context = LocalContext.current
    val intent : Intent = Intent(context, CreatePoll::class.java)
    context.startActivity(intent)
}

class CreatePoll : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d(TAG, "In CreatePoll class")
            ShowCreatedPolls()
            //TODO - Return to this screen
        }
    }
}


fun getCreatedPolls() : List<String> {
    Log.d(TAG, "in getCreatedPolls")
    return listOf("Create new poll")
}

//Ripped from MenuPollGrid
@Composable
fun ShowCreatedPolls() {
    Log.d(TAG, "in CreatePoll")
    val createdPolls = getCreatedPolls()
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        //PollOption(pollName = "Create New Poll")
        items(createdPolls.size) { index ->
            PollOption(pollName = createdPolls[index])
        }
    }
}

@Composable
fun PollOption(pollName: String) {
    //The display details of the card
    Log.d(TAG, "In PollOption()")
    var callbackFlag by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp) //TODO - make this 25% of the screen height
            .clickable(onClick = {
                Log.d(TAG, "Clicked on ${pollName}")
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
                text = pollName.uppercase(),
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            if (callbackFlag) {
                Log.d(TAG, "PollAnswer callback flag: ")
                //TODO - Handle multiple existing questions
            }

        }
    }
}

