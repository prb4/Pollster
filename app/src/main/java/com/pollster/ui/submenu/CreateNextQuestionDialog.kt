package com.pollster.ui.submenu

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.pollster.R

private val TAG: String = "Pollster - CreateNextQuestionDialog.kt"
@Composable
fun CreateNextQuestionDialog(
    onDismissRequest: () -> Unit,
    userChoice: (Int) -> Int
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card {
            Column() {
                Text(text = stringResource(id = R.string.create_poll_question),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
                TextButton(onClick = {
                    Log.d(TAG, "CHOICE_FINISH: ${R.integer.CHOICE_FINISH}")
                    userChoice(R.integer.CHOICE_FINISH)
                    onDismissRequest()}) {
                    Text(text = "Finish",
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                TextButton(onClick = { userChoice(R.integer.CHOICE_NEXT)
                    onDismissRequest() }) {
                    Text(text = "Next question",
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}