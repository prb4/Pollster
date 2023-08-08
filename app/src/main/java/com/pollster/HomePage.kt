package com.pollster

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.pollster.data.imageCardList
import com.pollster.support.ImageCardGrid

class HomePage : ComponentActivity() {

    val TAG: String = "ClothingMenu.kt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d(TAG, "In HomePage")
            // Call the function to display the RecyclerView
            ImageCardGrid(imageCardList = imageCardList)
        }
    }
}