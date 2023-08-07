package com.pollster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pollster.ui.theme.PollsterTheme

class Login : ComponentActivity() {
    var TAG : String = "Pollster - Login.kt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d(TAG, "In Login")
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PollsterTheme {
        Greeting("Android")
    }
}