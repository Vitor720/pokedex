package com.ddapps.pokedex.common.domain.models.response

import com.ddapps.pokedex.common.domain.models.response.pokemon.*


class PokemonDataResponse(val id: Int?,
                          val abilities: List<AbilitiesDataResponse>?,
                          val sprites: SpritesDataResponse?,
                          val stats: List<StatsDataResponse>,
                          val types: List<TypesDataResponse>,
                          val species: SpeciesDataResponse
)