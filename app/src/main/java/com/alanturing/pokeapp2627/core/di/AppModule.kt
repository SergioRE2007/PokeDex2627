package com.alanturing.pokeapp2627.core.di

import com.alanturing.pokeapp2627.features.pokemon.data.remote.PokemonDataSource
import com.alanturing.pokeapp2627.features.pokemon.data.remote.PokemonRemoteDataSource
import com.alanturing.pokeapp2627.features.pokemon.data.repository.PokemonRepository
import com.alanturing.pokeapp2627.features.pokemon.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindsPokemonRemoteDataSource(ds: PokemonRemoteDataSource): PokemonDataSource

    @Binds
    @Singleton
    fun bindsPokemonRepository(repository: PokemonRepositoryImpl): PokemonRepository
}
