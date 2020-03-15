package com.ddapps.pokedex.common.domain.models.ui


class Pokemon(val id: Int,
              val name: String,
              val abilities: Abilities,
              val stats: Stats,
              val type: PokeTypes,
              val evolution: Evolution,
              val varieties: List<Pokemon>?)