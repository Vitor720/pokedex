package com.ddapps.pokedex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddapps.pokedex.common.domain.models.ui.Pokemon
import com.ddapps.pokedex.common.domain.models.ui.SimplePokemon
import com.ddapps.pokedex.common.domain.usecase.PokemonUseCase
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.utils.DEFAULT_LIMIT
import com.ddapps.pokedex.utils.DEFAULT_OFFSET
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: PokemonUseCase) : ViewModel() {

    private var pokemonList = MutableLiveData<Resource<List<SimplePokemon>>>()

    private var singlePokemon = MutableLiveData<Resource<Pokemon>>()

    fun getPokemonList() =  pokemonList

    fun getSinglePokemonForRecycler() = singlePokemon

    fun getPokemonDisplay() = singlePokemon

    init {
        loadInitialPokemonList(DEFAULT_OFFSET, DEFAULT_LIMIT)
    }

    fun loadInitialPokemonList(offset: Int, limit: Int) {
        viewModelScope.launch {
            pokemonList.postValue(Resource.loading(null))
            pokemonList.postValue(useCase.getPokemonList(offset, limit))
        }
    }

    fun startPokemonQuery(query: String) {
        viewModelScope.launch {
            val pokemonResponse = useCase.searchPokemon(query)
            if (pokemonResponse.data != null) {
                val simplePokemon = listOf(SimplePokemon(pokemonResponse.data.name, null, null, null))
                pokemonList.postValue(Resource<List<SimplePokemon>>(pokemonResponse.status, simplePokemon, pokemonResponse.message))

            }
//            pokemonList.postValue(useCase.searchPokemon(query))
        }
    }


}
