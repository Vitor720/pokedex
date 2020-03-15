package com.ddapps.pokedex.utils

import com.ddapps.pokedex.common.domain.models.ui.Pokemon
import com.ddapps.pokedex.common.domain.models.response.PokemonListDataResponse

fun PokemonListDataResponse.mapForView() =
    Pokemon(this.count)