package com.ddapps.pokedex.common.domain.models.ui


class Pokemon(val id: Int,
              val name: String?,
              val abilities: List<Abilities?>,
              val stats: List<Stats>?,
              val type: List<PokemonTypes>?){

    private var evolution: MutableList<Evolution> = mutableListOf()
    private var varieties: MutableList<Pokemon> = mutableListOf()
}