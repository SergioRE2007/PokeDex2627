package com.alanturing.pokeapp2627.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.pokeapp2627.features.pokemon.data.model.Pokemon
import com.alanturing.pokeapp2627.features.pokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<ListUiState> =
        MutableStateFlow(ListUiState.Loading)

    val uiState: StateFlow<ListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val allPokemon = repository.readAllPokemon()
                _uiState.value = ListUiState.Success(allPokemon)
            } catch (e: Exception) {
                // Por ejemplo, si no hay internet
                _uiState.value = ListUiState.Error
            }
        }
    }
}

sealed class ListUiState {
    object Loading: ListUiState()
    object Error: ListUiState()
    data class Success(
        val pokemons: List<Pokemon>
    ): ListUiState()
}
