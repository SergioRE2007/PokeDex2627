package com.alanturing.pokeapp2627

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alanturing.pokeapp2627.ui.navigation.NavGraph
import com.alanturing.pokeapp2627.ui.theme.PokeApp2627Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeApp2627Theme {
                NavGraph()
            }
        }
    }
}
