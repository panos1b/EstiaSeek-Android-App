package com.example.estiaseek.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.estiaseek.R
import java.io.ByteArrayInputStream

@Composable
fun ImageCard(title: String, imageRes: ByteArray?, modifier: Modifier = Modifier) {
    val imageBitmap: ImageBitmap? = remember(imageRes) {
        byteArrayToImageBitmap(imageRes)
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        val contentDescription = title.takeIf { imageBitmap != null } ?: "Placeholder"

        imageBitmap?.let { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
        } ?: run {
            Image(
                painter = painterResource(id = R.drawable.dummy_restaurant_photo),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

fun byteArrayToImageBitmap(imageRes: ByteArray?): ImageBitmap? {
    val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(imageRes))
    return bitmap?.asImageBitmap()
}