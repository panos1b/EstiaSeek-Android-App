package com.example.estiaseek.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.estiaseek.R

@Composable
fun BottomNavigationBar(
    onSearchIconClicked: () -> Unit,
    onStartIconClicked: () -> Unit,
    onAddIconClicked: () -> Unit,
    showSearchIcon: Boolean = true,
    showStartIcon: Boolean = true,
    showAddIcon: Boolean = true
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        containerColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            if (showSearchIcon) {
                IconButton(
                    onClick = {
                        onSearchIconClicked()
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search_interface_symbol),
                        contentDescription = "Search",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            } else {
                IconButton(
                    enabled = false ,
                    onClick = {
                        onSearchIconClicked()
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search_screen),
                        contentDescription = "Search",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
            if (showStartIcon) {
                IconButton(
                    onClick = {
                        onStartIconClicked()
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Home",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
            if (showAddIcon) {
                IconButton(
                    onClick = {
                        onAddIconClicked()
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add_applicant_icon),
                        contentDescription = "Add",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            } else {
                IconButton(
                    enabled = false ,
                    onClick = {
                        onAddIconClicked()
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "Add",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}