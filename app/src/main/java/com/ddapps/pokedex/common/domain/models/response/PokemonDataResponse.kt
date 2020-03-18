package com.ddapps.pokedex.common.domain.models.response

import com.ddapps.pokedex.common.domain.models.response.pokemon.*


data class PokemonDataResponse(val id: Int?,
                          val name: String,
                          val abilities: List<AbilitiesDataResponse>?,
                          val sprites: SpritesDataResponse?,
                          val stats: List<StatsDataResponse>,
                          val types: List<TypesDataResponse>,
                          val pokemonSpecies: PokemonSpeciesDataResponse
)