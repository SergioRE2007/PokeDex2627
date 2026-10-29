package com.alanturing.pokeapp2627.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.alanturing.pokeapp2627.ui.common.UrlImage

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UrlImage(
            url = uiState.imageUrl,
            contentDescription = uiState.name,
            modifier = Modifier.size(256.dp)
        )
        Text(
            text = uiState.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Nº ${uiState.id}",
            style = MaterialTheme.typography.titleMedium
        )
    }
}
