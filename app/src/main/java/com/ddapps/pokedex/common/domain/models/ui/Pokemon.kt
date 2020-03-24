package com.ddapps.pokedex.common.domain.models.ui


class Pokemon(val id: Int,
              val name: String?,
              val abilities: List<Abilities>?,
              val stats: List<Stats>?,
              val type: List<PokemonTypes>?,
              val images: List<String?>){

    private var evolution: MutableList<Evolution> = mutableListOf()
    private var varieties: MutableList<Pokemon> = mutableListOf()
    var forms: MutableList<String> = mutableListOf()


    fun getPokemonName() = this.name?.capitalize()

    fun getPokemonID() = this.id.toString()

    fun getPokemonType(): List<String>? = this.type!!.map { it.name }

    //Colocar o id da textView com o nome exato para usar ele em uma função unica
    fun getPokemonHpStat() = this.stats?.find { it.name == "hp"}?.base_stat

    fun getPokemonSpeedStat() = this.stats?.find { it.name == "speed" }?.base_stat

    fun getPokemonSpecialAttackStat() = this.stats?.find { it.name == "special-attack" }?.base_stat

    fun getPokemonSpecialDefenseStat() = this.stats?.find { it.name == "special-defense" }?.base_stat

    fun getPokemonDefenseStat() = this.stats?.find { it.name == "defense" }?.base_stat

    fun getPokemonAttackStat() = this.stats?.find { it.name == "attack" }?.base_stat


}