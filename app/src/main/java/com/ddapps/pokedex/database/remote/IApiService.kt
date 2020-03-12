package com.ddapps.pokedex.database.remote

import com.ddapps.pokedex.common.domain.models.PokemonListDataResponse
import retrofit2.http.GET

interface IApiService {

    @GET("pokemon/")
    suspend fun getPokemonList(): PokemonListDataResponse


}