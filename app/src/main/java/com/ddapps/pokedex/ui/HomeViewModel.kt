package com.ddapps.pokedex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddapps.pokedex.common.domain.models.ui.Pokemon
import com.ddapps.pokedex.common.domain.usecase.PokemonUseCase
import com.ddapps.pokedex.data.remote.Resource
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: PokemonUseCase) : ViewModel() {

    private var pokemonList = MutableLiveData<Resource<List<Pokemon>>>()

    fun getPokemonList() = pokemonList

    fun updatePokemonList() {
        viewModelScope.launch {
            pokemonList.postValue(Resource.loading(null))
            pokemonList.postValue(useCase.getPokemonList())
        }
    }

    init {
        updatePokemonList()
    }

}
