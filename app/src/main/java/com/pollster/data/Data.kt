package com.pollster.data

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import com.pollster.ui.submenu.*

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
    var question: String,
    var answers: List<Answer>
) : java.io.Serializable

data class UserSelection(
    //String 1 is the actual question
    //String 2 is the actual answer
    //TODO - change this to a question ID and an answer ID
    val selection: Map<String, String> = emptyMap()
) {
    fun withSelection(question: String, answer: String): UserSelection {
        return copy(mapOf(question to answer))
    }
}

data class Answer(
    val answer: String,
    var isSelected: Boolean
) : java.io.Serializable

data class UserAnswer(
    val userAnswer: Map<Int, UserSelection> = emptyMap()
)

data class SelectionOption(
    val option: String,
    val isSelected: Boolean
)

//TODO - conditional questions based on another answer
val imageCardList = listOf(
    ImageCard(title = "Open Polls", description = "Polls that are available to me for answering", onClickCard = { OpenPollsBridge() }),
    ImageCard(title = "Create Permanent Group", description = "Create a private group", onClickCard = { OpenPolls() }),
    ImageCard(title = "Create 1 time poll", description = "Create a one time poll", onClickCard = { CreateNewPollBridge() }),
    ImageCard(title = "Public Polls", description = "See publicly available polls", onClickCard = { OpenPolls() }),
    ImageCard(title = "Stats", description = "See my stats", onClickCard = { OpenPolls() }),
    ImageCard(title = "Settings", description = "View/change my account settings", onClickCard = { OpenPolls() })
    // Add more image cards as needed
)
