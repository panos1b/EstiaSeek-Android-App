package com.example.estiaseek.ui.profile

import android.content.Intent
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.estiaseek.R
import com.example.estiaseek.ui.components.BottomNavigationBar
import com.example.estiaseek.ui.components.byteArrayToImageBitmap
import com.example.estiaseek.ui.navigation.NavigationHelper
import com.example.estiaseek.ui.viewmodels.ProfileViewModel


@OptIn(UnstableApi::class)
@Composable
fun ApplicantProfile(
    profileViewModel: ProfileViewModel,
    navController: NavController,
) {
    val context = LocalContext.current
    val profileViewState by profileViewModel.profileViewState.collectAsState()
    var isMuted by remember { mutableStateOf(true) } // Track mute state

    val videoPath = if (profileViewState.username.trim() == "Lil Pop") {
        "file:///android_asset/videos/test.mp4" //our evaluation video
    } else {
        "file:///android_asset/videos/${profileViewState.jobTitle.trim()}.mp4"
    }

    // Remember ExoPlayer instance
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(videoPath)))
            prepare()
            playWhenReady = true
            volume = if (isMuted) 0f else 1f
        }
    }

    LaunchedEffect(isMuted) {
        exoPlayer.volume = if (isMuted) 0f else 1f
    }

// Dispose of ExoPlayer correctly
    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onSearchIconClicked = { navController.navigate(NavigationHelper.Search.name) },
                onStartIconClicked = { navController.navigate(NavigationHelper.Start.name) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(rememberScrollState())
                    .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
            ) {
                // Video Player Section
                Box(
                    modifier = Modifier
                        .aspectRatio(16f / 9f)
                        .clickable {
                            // Toggle mute state on tap
                            isMuted = !isMuted
                            exoPlayer.volume = if (isMuted) 0f else 1f
                        }
                ) {
                    AndroidView(
                        factory = { ctx ->
                            PlayerView(ctx).apply {
                                player = exoPlayer
                                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                                useController = false // Hide video controls
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )

                    val imageBitmap = byteArrayToImageBitmap(profileViewState.photoData)
                    val title = profileViewState.username
                    val contentDescription =
                        title.takeIf { imageBitmap != null } ?: "Placeholder"
                    imageBitmap?.let { bitmap ->
                        Image(
                            bitmap = bitmap,
                            contentDescription = contentDescription,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .offset(x = 16.dp, y = 32.dp)
                                .size(width = 120.dp, height = 120.dp)
                                .clip(CircleShape)
                                .border(4.dp, Color.White, CircleShape)
                        )
                    } ?: run {
                        Image(
                            painter = painterResource(id = R.drawable.lilpop),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .offset(x = 16.dp, y = 32.dp)
                                .size(width = 120.dp, height = 120.dp)
                                .clip(CircleShape)
                                .border(4.dp, Color.White, CircleShape)
                        )
                    }
                }

                // User Details Section
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 30.dp)
                ) {
                    // User Name
                    Text(
                        text = profileViewState.username,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    // Location Chip
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = profileViewState.location,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }

                    // Bio Section
                    Text(
                        text = profileViewState.bio,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    // Job Title Section
                    Text(
                        text = stringResource(R.string.job_title),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = profileViewState.jobTitle,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    // Experience Section
                    Text(
                        text = stringResource(R.string.experience_level),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = profileViewState.experience,
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }


            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(paddingValues)
                    .padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp) // Optional spacing between elements
            ) {
                // Contact Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    val context = LocalContext.current

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data =
                                    Uri.parse("tel:${profileViewState.phoneNumber}")
                            }
                            context.startActivity(intent)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.call))
                    }

                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data =
                                    Uri.parse("mailto:${profileViewState.email}")
                            }
                            context.startActivity(intent)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.email))
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewApplicantProfile() {
    ApplicantProfile(
        profileViewModel = ProfileViewModel(),
        navController = NavController(LocalContext.current)
    )
}