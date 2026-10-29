package com.alanturing.pokeapp2627.ui.common

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

/**
 * Descarga una imagen de internet y la muestra.
 * Mientras se descarga (o si falla) deja el hueco vacío.
 */
@Composable
fun UrlImage(
    url: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    var bitmap by remember(url) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(url) {
        if (url != null) {
            // La descarga se hace en un hilo de fondo (Dispatchers.IO) para no congelar la app
            bitmap = withContext(Dispatchers.IO) {
                try {
                    URL(url).openStream().use { stream ->
                        BitmapFactory.decodeStream(stream)?.asImageBitmap()
                    }
                } catch (e: Exception) {
                    null
                }
            }
        }
    }

    val image = bitmap
    if (image != null) {
        Image(
            bitmap = image,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = ContentScale.Fit,
            filterQuality = FilterQuality.None, // los sprites son pixel art, así no se ven borrosos
        )
    } else {
        Box(modifier = modifier)
    }
}
