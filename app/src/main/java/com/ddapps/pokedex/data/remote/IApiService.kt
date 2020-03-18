package com.ddapps.pokedex.data.remote

import com.ddapps.pokedex.common.domain.models.response.PokemonDataResponse
import com.ddapps.pokedex.common.domain.models.response.PokemonListDataResponse
import com.ddapps.pokedex.common.domain.models.response.species.SpeciesDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiService {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offsset: Int,
        @Query("limit") limit: Int): PokemonListDataResponse

    @GET("pokemon/{pokemon_name}")
    suspend fun getSpecificPokemon(@Path("pokemon_name") pokemonName: String): PokemonDataResponse

    @GET("pokemon-species/{pokemon_id}")
    suspend fun getPokemonSpecies(@Path("pokemon_id") pokemonID: Int): SpeciesDataResponse


}