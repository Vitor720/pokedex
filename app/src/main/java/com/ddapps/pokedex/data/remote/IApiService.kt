package com.ddapps.pokedex.data.remote

import com.ddapps.pokedex.common.domain.models.response.PokemonListDataResponse
import retrofit2.http.GET

interface IApiService {

    @GET("pokemon/")
    suspend fun getPokemonList(): PokemonListDataResponse

}