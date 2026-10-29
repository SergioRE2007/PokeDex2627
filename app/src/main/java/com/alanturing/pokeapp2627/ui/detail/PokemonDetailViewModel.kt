package com.alanturing.pokeapp2627.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.alanturing.pokeapp2627.features.pokemon.data.repository.PokemonRepository
import com.alanturing.pokeapp2627.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String? = null,
)

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: PokemonRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<DetailUiState> =
        MutableStateFlow(DetailUiState())

    val uiState: StateFlow<DetailUiState>
        get() = _uiState.asStateFlow()

    init {
        // El id llega en la ruta con la que hemos navegado: Route.Detail(id)
        val pokemonId = savedStateHandle.toRoute<Route.Detail>().id

        viewModelScope.launch {
            try {
                val pokemon = repository.readPokemonById(pokemonId)
                if (pokemon != null) {
                    _uiState.value = DetailUiState(
                        id = pokemon.id,
                        name = pokemon.name,
                        imageUrl = pokemon.pokemonImageUrl,
                    )
                }
            } catch (e: Exception) {
                // Sin internet: se queda la pantalla vacía
            }
        }
    }
}
