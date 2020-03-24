package com.ddapps.pokedex.common.domain.models.response.pokemon

import com.ddapps.pokedex.common.domain.models.response.pokemon.StatDataResponse

class StatsDataResponse(val base_stat: Int?,
                        val effort: Int?,
                        val stat: StatDataResponse
)
