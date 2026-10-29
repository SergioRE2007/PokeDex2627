package com.alanturing.pokeapp2627.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object List: Route()

    @Serializable
    data class Detail(val id: Int): Route()
}
