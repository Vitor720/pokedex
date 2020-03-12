package com.ddapps.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddapps.pokedex.R
import com.ddapps.pokedex.database.remote.IApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val api: IApiService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
