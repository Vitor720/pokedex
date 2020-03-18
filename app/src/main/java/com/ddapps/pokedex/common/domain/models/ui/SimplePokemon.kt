package com.ddapps.pokedex.common.domain.models.ui

data class SimplePokemon(val name: String?,
                         val url: String?,
                         val nextPageUrl: String?,
                         val previousPageUrl: String?){

    var progress: Boolean = false


    fun getPokemonID(): String{
        val splitString = url!!.split("/")
        val id =  splitString[splitString.lastIndex - 1]
        return id
    }

    fun getPokemonName() = name!!.capitalize()


}