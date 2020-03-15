package com.ddapps.pokedex.common.domain.usecase

import com.ddapps.pokedex.common.domain.models.ui.Pokemon
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.data.remote.Status
import com.ddapps.pokedex.data.repository.PokemonRepository
import com.ddapps.pokedex.utils.mapForView

class PokemonUseCase(private val repository: PokemonRepository) {

   suspend fun getPokemonList(): Resource<List<Pokemon>>{
       val list = Resource<MutableList<Pokemon>>(Status.SUCCESS, null, null)
       val result = repository.getPokemonList()
       list.data?.add(result.mapForView())
       return list
   }
}