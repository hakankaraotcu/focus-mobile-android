package com.hakankaraotcu.focusquest.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hakankaraotcu.focusquest.ui.theme.FocusQuestTheme

@Composable
fun ConfirmDialog(
    isOpen: Boolean,
    title: String = "Are You Sure?",
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {
    if (isOpen) {
        AlertDialog(onDismissRequest = onDismissRequest,
            title = { Text(text = title) },
            text = {
                Text("This mission will strengthen your character. Did you really do it?")
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                TextButton(onClick = onConfirmButtonClick) {
                    Text(text = "Confirm")
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmDialogPreview() {
    FocusQuestTheme {
        ConfirmDialog(
            isOpen = true,
            title = "Are You Sure?",
            onDismissRequest = {},
            onConfirmButtonClick = {}
        )
    }
}