package com.ddapps.pokedex.di

import com.ddapps.pokedex.common.domain.usecase.PokemonUseCase
import com.ddapps.pokedex.data.remote.ResponseHandler
import com.ddapps.pokedex.data.remote.providePokemonApi
import com.ddapps.pokedex.data.remote.provideRetrofit
import com.ddapps.pokedex.data.repository.PokemonRepository
import com.ddapps.pokedex.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    factory { ResponseHandler() }
    factory { provideRetrofit() }
    factory { providePokemonApi(get()) }

}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

val useCaseModule = module {
    factory { PokemonUseCase(get()) }
}

val repositoryModule = module {
    single { PokemonRepository(get()) }
}



val modulesList = listOf(
    apiModule,
    viewModelModule,
    useCaseModule,
    repositoryModule
)