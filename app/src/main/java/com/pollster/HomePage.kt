package com.pollster

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pollster.ui.ui.theme.PollsterTheme
import com.pollster.data.ImageCard
import com.pollster.support.ImageCardGrid
import com.pollster.ui.submenu.OpenPolls

class HomePage : ComponentActivity() {

    val TAG: String = "ClothingMenu.kt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d(TAG, "In HomePage")
            Go()
        }
    }

    @Composable
    fun Go() {
        Log.d("Pollster - HomePage.kt", "In Go")
        //TODO - is this callback implementation sufficient?
        val imageCardList = listOf(
            ImageCard(title = "Open Polls", description = "Polls that are available to me for answering", onClickCard = { OpenPolls() }),
            ImageCard(title = "Create Permanent Group", description = "Create a private group", onClickCard = { OpenPolls() }),
            ImageCard(title = "Create 1 time poll", description = "Create a one time poll", onClickCard = { OpenPolls() }),
            ImageCard(title = "Public Polls", description = "See publicly available polls", onClickCard = { OpenPolls() }),
            ImageCard(title = "Stats", description = "See my stats", onClickCard = { OpenPolls() }),
            ImageCard(title = "Settings", description = "View/change my account settings", onClickCard = { OpenPolls() })
            // Add more image cards as needed
        )

        // Call the function to display the RecyclerView
        ImageCardGrid(imageCardList = imageCardList)
    }
}