package com.ddapps.pokedex.ui.fragments.tabs


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.ddapps.pokedex.R
import com.ddapps.pokedex.common.domain.models.ui.Pokemon
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.data.remote.Status
import com.ddapps.pokedex.databinding.FragmentEvolutionBinding
import com.ddapps.pokedex.ui.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EvolutionFragment : Fragment() {

    private var binding: FragmentEvolutionBinding? = null
    private val viewModel: HomeViewModel by sharedViewModel()

    private val observer = Observer<Resource<Pokemon>>{
        when(it.status){
            Status.SUCCESS -> {}
            Status.LOADING -> {}
            Status.ERROR   -> {}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_evolution, container, false)
        viewModel.getPokemonDisplay().observe(this, observer)
        return binding?.root
    }



}
