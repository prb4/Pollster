package com.pollster.ui.submenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.pollster.ui.submenu.ui.theme.PollsterTheme

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
    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            value = question,
            onValueChange = { question = it },
            label = { Text("Question") }
        )

    }
}