package com.example.kc.ui.views

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun <T> DropDownView(
    items: Array<T>,
    expanded: Boolean,
    onSelectClick: (Int, T) -> Unit,
    onDismiss: () -> Unit
) {
    // drop down menu
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        // adding items
        items.forEachIndexed { itemIndex, itemValue ->
            DropdownMenuItem(
                onClick = {
                    onSelectClick(itemIndex, itemValue)
                },
            ) {
                Text(text = itemValue.toString())
            }
        }
    }
}
