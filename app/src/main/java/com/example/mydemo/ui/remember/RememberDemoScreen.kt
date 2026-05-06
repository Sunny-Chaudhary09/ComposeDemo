package com.example.mydemo.ui.remember

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mydemo.R
import kotlin.random.Random

@Composable
fun RememberDemoScreen(
    onNavigateToLogin: () -> Unit
) {
    // These values are created only when this composable first enters the composition.
    // On Activity recreation, `remember` is lost, while `rememberSaveable` is restored.
    val sessionRememberId = remember { Random.nextInt(1000, 9999) }
    val sessionSaveableId = rememberSaveable { Random.nextInt(1000, 9999) }

    var rememberCounter by remember { mutableIntStateOf(0) }
    var rememberSaveableCounter by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_primary))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Remember vs RememberSaveable",
            style = MaterialTheme.typography.headlineMedium,
            color = colorResource(R.color.text_primary),
        )
        Text(
            text = "Tap buttons, then rotate the device (Activity recreation). " +
                "The `remember` counter resets, while `rememberSaveable` restores.",
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(R.color.text_secondary),
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "1) remember",
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(R.color.text_primary),
                )
                Text(
                    text = "sessionRememberId: $sessionRememberId",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.text_secondary),
                )
                Text(
                    text = "rememberCounter: $rememberCounter",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.text_primary),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Button(
                        onClick = { rememberCounter++ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary)),
                    ) {
                        Text("Increment")
                    }
                    OutlinedButton(
                        onClick = { rememberCounter = 0 },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("Reset")
                    }
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "2) rememberSaveable",
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(R.color.text_primary),
                )
                Text(
                    text = "sessionSaveableId: $sessionSaveableId",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.text_secondary),
                )
                Text(
                    text = "rememberSaveableCounter: $rememberSaveableCounter",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.text_primary),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Button(
                        onClick = { rememberSaveableCounter++ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary)),
                    ) {
                        Text("Increment")
                    }
                    OutlinedButton(
                        onClick = { rememberSaveableCounter = 0 },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("Reset")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToLogin,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary)),
        ) {
            Text("Open Login Screen")
        }
    }
}

