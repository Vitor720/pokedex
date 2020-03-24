package com.ddapps.pokedex.ui

import androidx.lifecycle.*
import com.ddapps.pokedex.common.domain.models.ui.*
import com.ddapps.pokedex.common.domain.usecase.PokemonUseCase
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.data.remote.Status
import com.ddapps.pokedex.utils.DEFAULT_LIMIT
import com.ddapps.pokedex.utils.DEFAULT_OFFSET
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val useCase: PokemonUseCase) : ViewModel() {

    private var pokemonDisplay = ""


    fun setPokemonToDisplay(pokemon: String) {
        pokemonDisplay = pokemon
    }


    fun getSamePokemonTypeList(type: Int) = liveData{
        emit(useCase.getPokemonListByType(type))
    }

    private var pokemonList = MutableLiveData<Resource<List<SimplePokemon>>>()

    fun getPokemonEvolutionChainID(pokemonID: Int) = liveData{
        emit(useCase.getPokemonEvolutionChainID(pokemonID))
    }

//    fun getPokemonChainEvolution(evolutionID: Int) = liveData {
//        emit(useCase.getEvolution(evolutionID))
//    }


    fun getPokemonList() =  pokemonList

    fun getPokemonDisplay() = liveData<Resource<Pokemon>>() {
        emit(useCase.searchPokemon(pokemonDisplay))
    }


    fun getAbility(id: Int) = liveData(){
        emit(useCase.getHability(id))
    }

    fun loadPokemonList(offset: Int, limit: Int) {
        viewModelScope.launch {
            pokemonList.postValue(Resource.loading(null))
            pokemonList.postValue(useCase.getPokemonList(offset, limit))
        }
    }

//    fun getPokemon(id: String){
//        viewModelScope.launch {
//            val pokemonResponse = useCase.searchPokemon(id)
//            singlePokemon.postValue(pokemonResponse)
//        }
//    }

    fun loadFirstList() = loadPokemonList(DEFAULT_OFFSET, DEFAULT_LIMIT)


}
