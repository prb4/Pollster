package com.pollster.data

import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.pollster.ui.submenu.OpenPolls
import com.pollster.ui.submenu.OpenPollsBridge

data class ImageCard(
    val title: String,
    val description: String,
    val onClickCard: @Composable () -> Unit
)

data class Poll(
    val title: String,
    val uuid: String,
    val pollQuestions: List<PollQuestion>
)

data class PollQuestion(
    val question: String,
    val answers: List<String>
) : java.io.Serializable

//TODO - conditional questions based on another answer
val imageCardList = listOf(
    ImageCard(title = "Open Polls", description = "Polls that are available to me for answering", onClickCard = { OpenPollsBridge() }),
    ImageCard(title = "Create Permanent Group", description = "Create a private group", onClickCard = { OpenPolls() }),
    ImageCard(title = "Create 1 time poll", description = "Create a one time poll", onClickCard = { OpenPolls() }),
    ImageCard(title = "Public Polls", description = "See publicly available polls", onClickCard = { OpenPolls() }),
    ImageCard(title = "Stats", description = "See my stats", onClickCard = { OpenPolls() }),
    ImageCard(title = "Settings", description = "View/change my account settings", onClickCard = { OpenPolls() })
    // Add more image cards as needed
)
