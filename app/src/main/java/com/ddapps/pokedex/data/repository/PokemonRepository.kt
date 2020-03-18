package com.ddapps.pokedex.data.repository

import com.ddapps.pokedex.common.domain.models.response.PokemonDataResponse
import com.ddapps.pokedex.common.domain.models.response.PokemonListDataResponse
import com.ddapps.pokedex.common.domain.models.ui.Pokemon
import com.ddapps.pokedex.common.domain.models.ui.SimplePokemon
import com.ddapps.pokedex.data.remote.*
import com.ddapps.pokedex.utils.mapForView
import timber.log.Timber
import kotlin.Exception

class PokemonRepository(private val api: IApiService, private val responseHandler: ResponseHandler) {

   private var simplePokemonList = mutableListOf<SimplePokemon>()

   fun getSimplePokemonList() = simplePokemonList

   suspend fun getSpecificPokemon(pokemon: String): Resource<PokemonDataResponse>{
      return try {
         val response = api.getSpecificPokemon(pokemon)
         responseHandler.handleSuccess(response)
      } catch (e: Throwable){
         responseHandler.handleThrowable(e)
      }
   }

   suspend fun getPokemonList(offsset: Int, limit: Int): Resource<PokemonListDataResponse> {
      return try {
         val response = api.getPokemonList(offsset, limit)
         responseHandler.handleSuccess(response)
      } catch (t:Throwable){
         responseHandler.handleThrowable(t)
      }
   }
}