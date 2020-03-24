package com.ddapps.pokedex.ui.fragments.tabs

import android.content.Context
import android.net.Uri
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
import com.ddapps.pokedex.databinding.FragmentStatsBinding
import com.ddapps.pokedex.ui.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StatsFragment : Fragment() {
    private var binding: FragmentStatsBinding? = null
    private val viewModel: HomeViewModel by sharedViewModel()




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stats, container, false)

        val observer = Observer<Resource<Pokemon>> {
            when (it.status) {
                Status.SUCCESS -> {

                    binding?.textViewHP?.text = it.data?.getPokemonHpStat().toString()
                    binding?.textViewAttack?.text = it.data?.getPokemonAttackStat().toString()
                    binding?.textViewDefense?.text = it.data?.getPokemonDefenseStat().toString()
                    binding?.textViewSpAtk?.text = it.data?.getPokemonSpecialAttackStat().toString()
                    binding?.textViewSpDef?.text = it.data?.getPokemonSpecialDefenseStat().toString()
                    binding?.textViewSpeed?.text = it.data?.getPokemonSpeedStat().toString()

                    binding?.progressBarHP?.progress = it.data?.getPokemonHpStat() ?: 0
                    binding?.progressBarAttack?.progress = it.data?.getPokemonAttackStat() ?: 0
                    binding?.progressBarDefense?.progress = it.data?.getPokemonDefenseStat() ?: 0
                    binding?.progressBarSpAtk?.progress = it.data?.getPokemonSpecialAttackStat() ?: 0
                    binding?.progressBarSpDef?.progress = it.data?.getPokemonSpecialDefenseStat() ?: 0
                    binding?.progressBarSpeed?.progress = it.data?.getPokemonSpeedStat() ?: 0

                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        }


        viewModel.getPokemonDisplay().observe(this, observer)
        return binding?.root
        }


}

