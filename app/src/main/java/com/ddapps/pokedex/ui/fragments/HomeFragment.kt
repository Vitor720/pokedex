package com.ddapps.pokedex.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddapps.pokedex.R
import com.ddapps.pokedex.common.adapter.PokemonListAdapter
import com.ddapps.pokedex.common.domain.models.ui.SimplePokemon
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.data.remote.Status
import com.ddapps.pokedex.databinding.HomeFragmentBinding
import com.ddapps.pokedex.ui.HomeViewModel
import com.ddapps.pokedex.utils.DEFAULT_LIMIT
import com.ddapps.pokedex.utils.EndlessRecyclerViewScrollListener
import com.ddapps.pokedex.utils.IOnclickListener
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment(), IOnclickListener {

    private val viewModel: HomeViewModel by sharedViewModel()
    private var binding: HomeFragmentBinding? = null
    private var adapter: PokemonListAdapter? = null

    private val observer = Observer<Resource<List<SimplePokemon>>> {
        when (it.status) {
            Status.SUCCESS -> {
                loadRecycler(it.data!!)
            }
            Status.ERROR -> {
                showDialog(it.status.toString() , it.message ?: "Ops, Somenthing went wrong")
            }
            Status.LOADING -> {}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container,false)
        binding?.viewModel = viewModel

        viewModel.loadFirstList()

        viewModel.getPokemonList().observe(this, observer)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun loadRecycler(pokemonList: List<SimplePokemon>){
        if (adapter != null) {
            adapter?.addMoreItems(pokemonList)
        } else {
            val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding?.pokemonListRecycler?.layoutManager = layoutManager
            adapter = PokemonListAdapter(pokemonList, this)
            binding?.pokemonListRecycler?.adapter = adapter

            setUpScrollListener(layoutManager)
        }
    }

    private fun setUpScrollListener(layoutManager: StaggeredGridLayoutManager) {
        val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    adapter?.setLoading()
                    val offset = page * DEFAULT_LIMIT
                    viewModel.loadPokemonList(offset, DEFAULT_LIMIT)
                    adapter?.setLoaded()
            }
        }
        binding?.pokemonListRecycler?.addOnScrollListener(scrollListener)
    }



    override fun onClick(id: Int) {
        viewModel.setPokemonToDisplay(id.toString())
        adapter = null
        findNavController().navigate(R.id.action_homeFragment_to_pokemonDisplayFragment)
    }

    private fun showDialog(header: String, body: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context!!)
        builder.setTitle(header)
        builder.setMessage(body)
        builder.setPositiveButton("close") { dialogInterface, i ->
            Snackbar.make(
                binding?.root!!,
                "",
                Snackbar.LENGTH_SHORT
            )
        }
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.update -> viewModel.loadFirstList()
        }
        return super.onOptionsItemSelected(item)
    }

}
