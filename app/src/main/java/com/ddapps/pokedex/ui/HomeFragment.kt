package com.ddapps.pokedex.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddapps.pokedex.R
import com.ddapps.pokedex.common.adapter.PokemonListAdapter
import com.ddapps.pokedex.common.domain.models.ui.SimplePokemon
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.data.remote.Status
import com.ddapps.pokedex.databinding.HomeFragmentBinding
import com.ddapps.pokedex.utils.DEFAULT_LIMIT
import com.ddapps.pokedex.utils.EndlessRecyclerViewScrollListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by sharedViewModel()
    private var binding: HomeFragmentBinding? = null
    private var adapter: PokemonListAdapter? = null

    private val observer = Observer<Resource<List<SimplePokemon>>> {
        when (it.status) {
            Status.SUCCESS -> {
                loadRecycler(it.data!!)
//                binding?.pokemonListRecycler?.swapVisibility()
             }
            Status.ERROR -> {
//                binding?.pokemonListRecycler?.swapVisibility()
            }
            Status.LOADING -> {
//                binding?.pokemonListRecycler?.swapVisibility()
//                Toast.makeText(context, "Carregando", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container,false)
        binding?.viewModel = viewModel
        binding?.executePendingBindings()
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getPokemonList().observe(this, observer)
    }

    fun loadRecycler(pokemonList: List<SimplePokemon>){
        if (adapter == null) {
            val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding?.pokemonListRecycler?.layoutManager = layoutManager
            adapter = PokemonListAdapter(pokemonList)
            binding?.pokemonListRecycler?.adapter = adapter

            setUpScrollListener(layoutManager)
        } else {
            adapter?.addMoreItems(pokemonList)
        }
    }

    private fun setUpScrollListener(layoutManager: StaggeredGridLayoutManager) {
        val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if(totalItemsCount > 2){
                    Timber.e("passou aqui")
                    adapter?.setLoading()
                    val offset = page * DEFAULT_LIMIT
                    viewModel.loadInitialPokemonList(offset, DEFAULT_LIMIT)
                    adapter?.setLoaded()
                }

            }
        }
        binding?.pokemonListRecycler?.addOnScrollListener(scrollListener)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> activity?.onSearchRequested()
        }
        return super.onOptionsItemSelected(item)
    }
}
