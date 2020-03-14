package com.ddapps.pokedex.data.remote

import com.ddapps.pokedex.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}


fun providePokemonApi(retrofit: Retrofit): IApiService {
    return retrofit.create(IApiService::class.java)
}
