package com.alanturing.pokeapp2627.features.pokemon.data.remote

import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon
import javax.inject.Inject

class PokemonRemoteDataSource
    @Inject constructor(
        private val _api:PokeApi
    ): PokemonDataSource {
    override suspend fun readAllPokemon(): List<Pokemon> {
        val response = _api.readAllPokemon()
        val pokemons = mutableListOf<Pokemon>()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            // La lista solo trae el nombre, así que pedimos el detalle de cada uno
            for(item in body.results) {
                val pokemon = readPokemonByName(item.name)
                if (pokemon != null) {
                    pokemons.add(pokemon)
                }
            }
        }
        return pokemons
    }

    override suspend fun readPokemonByName(name: String): Pokemon? {
        val response = _api.readPokemonByName(name)
        val body = response.body()
        if (response.isSuccessful && body != null) {
            return body.toModel()
        }
        else{
            return null
        }
    }

    override suspend fun readPokemonById(id: Int): Pokemon? {
        val response = _api.readPokemonById(id)
        val body = response.body()
        if (response.isSuccessful && body != null) {
            return body.toModel()
        }
        else{
            return null
        }
    }

}
