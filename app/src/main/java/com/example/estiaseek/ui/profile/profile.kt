import android.content.Intent
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView


@OptIn(UnstableApi::class)
@Composable
fun Profile() {
    val context = LocalContext.current

    // Remember ExoPlayer instance
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse("file:///android_asset/test.mp4")))
            prepare()
            playWhenReady = true
            volume = 0f // Mute by default
        }
    }

    var isMuted by remember { mutableStateOf(true) } // Track mute state

    // Dispose of ExoPlayer correctly
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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

            // Profile Picture Overlay
            Image(
                painter = rememberAsyncImagePainter("file:///android_asset/lilpop.jpg"), // Load from assets
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

        // User Details Section
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 30.dp)
        ) {
            // User Name
            Text(
                text = "Lil Pop",
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
                    text = "Las Vegas, NV",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            // Bio Section
            Text(
                text = "As a dedicated waiter with 87 years of experience in the hospitality industry, I pride myself on delivering exceptional dining experiences through top-tier customer service, attention to detail, and a positive attitude. Whether in fast-paced casual dining or upscale restaurants, I thrive in environments where I can connect with guests, anticipate their needs, and ensure every visit is memorable.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Experience Section
            Text(
                text = "Experience:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "1. Delivered exceptional service in a fast-paced, 100+ seat restaurant, managing up to 8 tables at a time while maintaining a 98% customer satisfaction rating based on guest feedback surveys.\n" +
                        "2. Increased beverage sales by 20% through effective upselling techniques, menu knowledge, and personalized recommendations tailored to guest preferences.\n" +
                        "3. Trained and mentored a team of 5 new waitstaff, improving service efficiency and reducing order errors by 15% within the first three months.",
                style = MaterialTheme.typography.bodyMedium
            )

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
                                Uri.parse("tel:+1234567890") // Replace with the desired phone number
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Call")
                }

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data =
                                Uri.parse("mailto:example@example.com") // Replace with the desired email address
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Email")
                }

            }
        }
    }

    // Dispose of ExoPlayer when the composable is disposed
    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }
}

@Preview
@Composable
fun PreviewProfile() {
    Profile()
}
