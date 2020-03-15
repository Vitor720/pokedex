package com.ddapps.pokedex.common.domain.models.response

class PokemonListDataResponse(val count: Int,
                              val next: String?,
                              val previous: Boolean?,
                              val results: List<PokemonListResultsDataResponse>?)