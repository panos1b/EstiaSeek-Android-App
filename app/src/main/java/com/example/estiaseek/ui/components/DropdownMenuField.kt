package com.example.estiaseek.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun DropdownMenuField(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    options: List<String>,
    selectedOption: String? = null,
    onOptionSelected: (String) -> Unit,
    icon: ImageVector? = null
) {
    val initialSelected = selectedOption?.takeIf { it.isNotEmpty() } ?: options.firstOrNull().orEmpty()
    var selected by remember { mutableStateOf(initialSelected) }
    var expanded by remember { mutableStateOf(false) }

    // Animation for dropdown arrow rotation
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "dropdown rotation"
    )

    Box(modifier = modifier) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            shape = RoundedCornerShape(12.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (icon != null) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = if (expanded)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column {
                        Text(
                            text = stringResource(label),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                        Text(
                            text = selected.ifEmpty { "Select" },
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (selected.isEmpty())
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotationState),
                    tint = if (expanded)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(12.dp)
                )
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (option == selected)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    },
                    onClick = {
                        selected = option
                        onOptionSelected(option)
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}