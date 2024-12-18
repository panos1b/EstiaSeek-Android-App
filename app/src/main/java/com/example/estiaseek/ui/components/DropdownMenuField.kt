package com.example.estiaseek.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.estiaseek.R
import androidx.compose.foundation.border
import androidx.compose.ui.Alignment
import androidx.compose.foundation.background


@Composable
fun DropdownMenuField(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    options: List<String>,
    selectedOption: String? = null,
    onOptionSelected: (String) -> Unit
) {
    // Ensure a valid initial selected option
    val initialSelected = selectedOption?.takeIf { it.isNotEmpty() } ?: options.firstOrNull().orEmpty()

    // Initialize the selected option state
    var selected by remember { mutableStateOf(initialSelected) }
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        // Main clickable box
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Label and selected option
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(label),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = selected,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Dropdown arrow icon
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selected = option // Update the selected option
                        onOptionSelected(option) // Notify parent
                        expanded = false
                    }
                )
            }
        }
    }
}