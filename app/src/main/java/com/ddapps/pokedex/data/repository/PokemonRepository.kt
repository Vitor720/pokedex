package com.ddapps.pokedex.data.repository

import com.ddapps.pokedex.data.remote.IApiService

class PokemonRepository(private val api: IApiService) {

   suspend fun getPokemonList() = api.getPokemonList()
}