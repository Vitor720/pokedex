package com.ddapps.pokedex.utils

import com.ddapps.pokedex.common.domain.models.Pokemon
import com.ddapps.pokedex.common.domain.models.PokemonListDataResponse

fun PokemonListDataResponse.mapForView() = Pokemon(this.count)