package com.ddapps.pokedex.data.repository

import com.ddapps.pokedex.common.domain.models.response.PokemonDataResponse
import com.ddapps.pokedex.common.domain.models.response.PokemonListDataResponse
import com.ddapps.pokedex.common.domain.models.response.ability.FullAbilityDataResponse
import com.ddapps.pokedex.common.domain.models.response.evolution.FullEvolutionDataResponse
import com.ddapps.pokedex.common.domain.models.response.species.SpeciesDataResponse
import com.ddapps.pokedex.common.domain.models.response.types.FullTypeResponse
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

   suspend fun getAbility(id: Int): Resource<FullAbilityDataResponse>{
      return try {
          val response = api.getAbility(id)
         responseHandler.handleSuccess(response)
      } catch (t: Throwable){
         responseHandler.handleThrowable(t)
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

   suspend fun getPokemonSpecies(pokemonID: Int): Resource<SpeciesDataResponse> {
      return try {
         val response = api.getPokemonSpecies(pokemonID)
         responseHandler.handleSuccess(response)
      } catch (t: Throwable){
         responseHandler.handleThrowable(t)
      }


    }

   suspend fun getPokemonListByType(pokemonID: Int): Resource<FullTypeResponse>{
      return try {
         val response = api.getPokemonType(pokemonID)
         responseHandler.handleSuccess(response)
      } catch (t: Throwable){
         responseHandler.handleThrowable(t)
      }
   }


   suspend fun getEvolution(evolutionID: Int): Resource<FullEvolutionDataResponse> {
      return try {
         val response = api.getEvolutionChain(evolutionID)
         responseHandler.handleSuccess(response)
      } catch(t: Throwable){
         responseHandler.handleThrowable(t)
      }
   }
}