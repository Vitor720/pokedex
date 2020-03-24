package com.ddapps.pokedex.data.remote

import com.ddapps.pokedex.common.domain.models.response.PokemonDataResponse
import com.ddapps.pokedex.common.domain.models.response.PokemonListDataResponse
import com.ddapps.pokedex.common.domain.models.response.ability.FullAbilityDataResponse
import com.ddapps.pokedex.common.domain.models.response.evolution.FullEvolutionDataResponse
import com.ddapps.pokedex.common.domain.models.response.pokemon.AbilityDataResponse
import com.ddapps.pokedex.common.domain.models.response.species.SpeciesDataResponse
import com.ddapps.pokedex.common.domain.models.response.types.FullTypeResponse
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

    @GET("ability/{ability_id}")
    suspend fun getAbility(@Path("ability_id") abilityID: Int): FullAbilityDataResponse

    @GET("evolution-chain/{evolution_chain_id}")
    suspend fun getEvolutionChain(@Path("evolution_chain_id") evolutionChainID: Int): FullEvolutionDataResponse

    @GET("type/{pokemon_type}")
    suspend fun getPokemonType(@Path("pokemon_type") pokemonType: Int): FullTypeResponse


}