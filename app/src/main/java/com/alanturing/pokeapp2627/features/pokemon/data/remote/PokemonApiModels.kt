package com.alanturing.pokeapp2627.features.pokemon.data.remote

import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonResult(
    val id:Int,
    val name: String,
    val sprites: PokemonSprites
)
data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault:String?,
)
data class PokemonListResult(
    val results: List<PokemonListItemResult>
)
data class PokemonListItemResult(
    val name:String,
    val url: String,
)

fun PokemonResult.toModel(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        pokemonImageUrl = this.sprites.frontDefault,
    )

}
