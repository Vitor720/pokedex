package com.ddapps.pokedex.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddapps.pokedex.R
import com.ddapps.pokedex.common.domain.models.Pokemon
import com.ddapps.pokedex.databinding.RowPokemonBinding
import kotlinx.android.synthetic.main.row_pokemon.view.*

class PokemonListAdapter(val pokemonList: List<Pokemon>): RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    private var filteredPokemonList = mutableListOf<Pokemon>()

    init {
        filteredPokemonList.addAll(pokemonList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListAdapter.PokemonListViewHolder {
        val view: RowPokemonBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_pokemon, parent, false)
        return PokemonListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredPokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.PokemonListViewHolder, position: Int) {
        val item = pokemonList[position]
        holder.bind(item)
    }

    inner class PokemonListViewHolder internal constructor(val binding: RowPokemonBinding): RecyclerView.ViewHolder(binding.root){
        val rowLayout = binding.rowLayout

        fun bind(item: Pokemon){
            binding.pokemon = item
            binding.executePendingBindings()
        }
    }

    fun updateList(newPokemonList: List<Pokemon>){
        filteredPokemonList.clear()
        filteredPokemonList.addAll(newPokemonList)
        notifyDataSetChanged()
    }
}