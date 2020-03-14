package com.ddapps.pokedex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddapps.pokedex.common.domain.models.Pokemon
import com.ddapps.pokedex.common.domain.models.usecase.PokemonUseCase
import com.ddapps.pokedex.data.remote.Resource

class HomeViewModel(private val useCase: PokemonUseCase) : ViewModel() {

    private var pokemonList = MutableLiveData<Resource<List<Pokemon>>>()

    fun getPokemonList() = pokemonList

    suspend fun updatePokemonList(){
        pokemonList.postValue(Resource.loading(null))
        pokemonList.postValue(useCase.getPokemonList())
    }

}
