package com.example.estiaseek

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.estiaseek.ui.components.BottomNavigationBar
import com.example.estiaseek.ui.components.ImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = false)
@Composable
fun ResultsScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
    { paddingValues ->

        Column(modifier = Modifier.padding(35.dp).padding(paddingValues)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.results),
                    fontSize = 35.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.results_based_on_search_terms),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            val filters = listOf("Waitress", "Crete", "Senior")

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filters.size) { index ->
                            var selected by remember { mutableStateOf(false) }
                            InputChip(
                                selected = selected,
                                onClick = { selected = !selected },
                                label = { Text(filters[index]) },
                                trailingIcon = {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Localized description",
                                        Modifier.size(InputChipDefaults.IconSize),
                                        tint = Color.White
                                    )
                                },
                                colors = InputChipDefaults.inputChipColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    labelColor = Color.White
                                ),
                                border = InputChipDefaults.inputChipBorder(
                                    borderColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }

                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                    ) {
                        items(5) { index ->
                            ImageCard(
                                title = "Place ${index + 1}",
                                imageRes = R.drawable.dummy_restaurant_photo
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.dummy_restaurant_photo),
                            contentDescription = "Main restaurant",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                }

                items(10) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ImageCard(
                            title = "Image $index",
                            imageRes = R.drawable.dummy_restaurant_photo,
                            modifier = Modifier.weight(1f)
                        )
                        ImageCard(
                            title = "Image ${index + 1}",
                            imageRes = R.drawable.dummy_restaurant_photo,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun PreviewResults() {
    ResultsScreen()
}

@Composable
@Preview(showBackground = false)
fun PreviewBottomNavigationBar() {
    val navController = rememberNavController()
    BottomNavigationBar(navController = navController)
}