package com.ddapps.pokedex.common.domain.usecase

import com.ddapps.pokedex.common.domain.models.response.PokemonDataResponse
import com.ddapps.pokedex.common.domain.models.response.PokemonListDataResponse
import com.ddapps.pokedex.common.domain.models.ui.Pokemon
import com.ddapps.pokedex.common.domain.models.ui.SimplePokemon
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.data.remote.Status
import com.ddapps.pokedex.data.repository.PokemonRepository
import com.ddapps.pokedex.utils.mapForView

class PokemonUseCase(private val repository: PokemonRepository) {

    suspend fun searchPokemon(pokemon: String): Resource<Pokemon>{
        val result: Resource<PokemonDataResponse> = repository.getSpecificPokemon(pokemon)

        return Resource(result.status, result.data?.mapForView(), result.message)
    }

   suspend fun getPokemonList(offsset: Int, limit: Int): Resource<List<SimplePokemon>>{
       val result: Resource<PokemonListDataResponse> = repository.getPokemonList(offsset, limit)
       val simplePokemonList = result.data!!.mapForView()
       return Resource<List<SimplePokemon>>(result.status, simplePokemonList, result.message)
   }
}