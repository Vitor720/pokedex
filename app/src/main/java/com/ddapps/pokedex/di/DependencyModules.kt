package com.ddapps.pokedex.di

import com.ddapps.pokedex.database.remote.ResponseHandler
import com.ddapps.pokedex.database.remote.providePokemonApi
import com.ddapps.pokedex.database.remote.provideRetrofit
import org.koin.dsl.module

val apiModule = module {
    factory { ResponseHandler() }
    factory { provideRetrofit() }
    factory { providePokemonApi(get()) }

}

val modulesList = listOf(
    apiModule
)