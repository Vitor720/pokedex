package com.ddapps.pokedex.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.ddapps.pokedex.R
import com.ddapps.pokedex.common.adapter.PokemonListAdapter
import com.ddapps.pokedex.common.domain.models.Pokemon
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.data.remote.Status
import com.ddapps.pokedex.databinding.HomeFragmentBinding
import com.ddapps.pokedex.utils.swapVisibility
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var binding: HomeFragmentBinding? = null
    private val adapter: PokemonListAdapter? = null

    private val observer = Observer<Resource<List<Pokemon>>> {
        when (it.status) {
            Status.SUCCESS -> {
                loadRecycler(it.data!!)
                binding?.pokemonListRecycler?.swapVisibility()
             }
            Status.ERROR -> {
                binding?.pokemonListRecycler?.swapVisibility()
            }
            Status.LOADING -> {
                binding?.pokemonListRecycler?.swapVisibility()
                Toast.makeText(context, "Carregando", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container,false)
        binding?.viewModel = viewModel
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    fun loadRecycler(pokemonList: List<Pokemon>){
        if (adapter == null) {
            val spanCount = 3
            val layoutManager = GridLayoutManager(context, spanCount)
            layoutManager.orientation
            val alphaAdapter = AlphaInAnimationAdapter(PokemonListAdapter(pokemonList)).apply { setDuration(720) }
            binding?.pokemonListRecycler?.adapter = alphaAdapter
        } else {
            adapter.updateList(pokemonList)
        }
    }

}
