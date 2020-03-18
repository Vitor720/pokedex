package com.ddapps.pokedex.utils

import com.ddapps.pokedex.common.domain.models.response.PokemonDataResponse
import com.ddapps.pokedex.common.domain.models.response.PokemonListDataResponse
import com.ddapps.pokedex.common.domain.models.ui.*

fun PokemonListDataResponse.mapForView(): List<SimplePokemon> {
    val simplePokemonList = mutableListOf<SimplePokemon>()
    if (this.results != null) {
        for(response in this.results){
            val pokemon = SimplePokemon(response.name, response.url, this.next, this.previous)
            simplePokemonList.add(pokemon)
        }
    }
    return simplePokemonList as List<SimplePokemon>
}

fun PokemonDataResponse.mapForView(): Pokemon {
    val abilitys = mutableListOf<Abilities>()
    for (i in this.abilities!!.iterator()) {
        val thisAbility = Abilities(i.ability!!.name, i.ability.url)
        abilitys.add(thisAbility)
    }

    val stats = mutableListOf<Stats>()
    for (i in this.stats.iterator()) {
        val newstat = Stats(i.stat.name, i.stat.url)
        stats.add(newstat)
    }

    val types = mutableListOf<PokemonTypes>()
    for (i in this.types.iterator()){
        val newType = PokemonTypes(i.type.name!!, i.type.url!!)
        types.add(newType)
     }

    val pokemon = this.let {
        Pokemon(id = it.id!!, name = it.name, abilities = abilitys, stats = stats, type = types)
    }
    return pokemon

}