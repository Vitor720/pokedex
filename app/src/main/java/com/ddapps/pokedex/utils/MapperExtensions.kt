package com.ddapps.pokedex.utils

import com.ddapps.pokedex.common.domain.models.response.PokemonDataResponse
import com.ddapps.pokedex.common.domain.models.response.PokemonListDataResponse
import com.ddapps.pokedex.common.domain.models.response.ability.FullAbilityDataResponse
import com.ddapps.pokedex.common.domain.models.response.evolution.FullEvolutionDataResponse
import com.ddapps.pokedex.common.domain.models.response.types.FullTypeResponse
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
        val newstat = Stats(i.stat.name, i.base_stat ,i.stat.url)
        stats.add(newstat)
    }

    val types = mutableListOf<PokemonTypes>()
    for (i in this.types.iterator()){
        val newType = PokemonTypes(i.type.name!!, i.type.url!!)
        types.add(newType)
     }

    val images = mutableListOf(this.sprites?.front_shiny,
                                this.sprites?.back_default,
                                this.sprites?.back_shiny,
                                this.sprites?.front_shiny,
                                this.sprites?.front_default)

    val pokemon = this.let {
        Pokemon(id = it.id!!, name = it.name, abilities = abilitys, stats = stats, type = types, images = images)
    }

    return pokemon

}

fun FullTypeResponse.mapForView(): String{
    var pokemons = ""
    for (i in this.pokemon.iterator()){
        val cleanPokemonName = i.pokemon.name!!.replace("-", " ").capitalize()
        pokemons += "$cleanPokemonName \n"
    }
    return pokemons
}


fun FullAbilityDataResponse.mapForView(): FullAbility{
    return FullAbility(name!!, id!!, effect_entries?.first()?.effect!!)
}

//fun FullEvolutionDataResponse.mapForView(): Evolution{
//    var evolutionList = mutableListOf<Evolution>()
//    for (i in this.chain.evolves_to.iterator()){
//        val evolution = Evolution(this.chain.species!!.name, this.chain.species.url, listOf(this.chain.evolves_to!!.firstOrNull().) )
//    }
//    val evolution = Evolution(this.chain.species!!.name, this.chain.species.url, this.chain.evolves_to )
//}