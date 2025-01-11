package com.example.estiaseek.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomSnackbar(
    snackbarData: SnackbarData,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = snackbarData.visuals.message,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}