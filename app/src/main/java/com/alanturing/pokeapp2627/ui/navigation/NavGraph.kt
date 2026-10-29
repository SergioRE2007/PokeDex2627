package com.alanturing.pokeapp2627.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alanturing.pokeapp2627.ui.detail.PokemonDetailScreen
import com.alanturing.pokeapp2627.ui.list.PokemonListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = "PokeDex") })
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.List
        ) {
            composable<Route.List> {
                PokemonListScreen(
                    modifier = Modifier.padding(innerPadding),
                    onShowDetail = { id ->
                        navController.navigate(Route.Detail(id))
                    }
                )
            }
            composable<Route.Detail> {
                PokemonDetailScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
