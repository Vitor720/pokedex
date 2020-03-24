package com.ddapps.pokedex.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ddapps.pokedex.R
import com.ddapps.pokedex.common.domain.models.ui.SimplePokemon
import com.ddapps.pokedex.databinding.RowPokemonBinding
import com.ddapps.pokedex.utils.IOnclickListener
import com.ddapps.pokedex.utils.VIEW_ITEM
import com.ddapps.pokedex.utils.VIEW_PROGRESS

class PokemonListAdapter(val pokemonList: List<SimplePokemon>, val clickListener: IOnclickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var loading = false

    private var filteredPokemonList = mutableListOf<SimplePokemon>()

    private var firstLoad = true

    init {
        filteredPokemonList.addAll(pokemonList)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == filteredPokemonList.size.minus(1) ) VIEW_PROGRESS else VIEW_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{

        return if (viewType == VIEW_ITEM) {
            val view: RowPokemonBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_pokemon, parent, false)
            PokemonListViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
            ProgressViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return filteredPokemonList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = filteredPokemonList[position]
        when(holder){
            is PokemonListViewHolder -> {holder.bind(item); holder.rowLayout.setOnClickListener { clickListener.onClick(item.getPokemonID().toInt()) }}
            is ProgressViewHolder -> { holder.progressBar.isIndeterminate = true }
        }

        if (item.progress) {
            val layoutParams: StaggeredGridLayoutManager.LayoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
            layoutParams.isFullSpan = true
        } else {
            val layoutParams: StaggeredGridLayoutManager.LayoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
            layoutParams.isFullSpan = false
        }
    }

    inner class PokemonListViewHolder internal constructor(val binding: RowPokemonBinding): RecyclerView.ViewHolder(binding.root){
        val rowLayout = binding.rowLayout

        fun bind(item: SimplePokemon){
            binding.pokemon = item
            binding.executePendingBindings()
        }
    }

    class ProgressViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var progressBar: ProgressBar = v.findViewById<View>(R.id.progress_bar) as ProgressBar
    }

    fun setLoaded() {
        loading = false
        for (i in 0 until itemCount) {
            if (filteredPokemonList[i].progress) {
                filteredPokemonList.removeAt(i)
                notifyItemRemoved(i)
            }
        }
    }

    fun setLoading() {
        if (itemCount == 0 ) {
            val pokemon = filteredPokemonList.first().copy().apply { this.progress = true }
            filteredPokemonList.add(pokemon)
            notifyItemInserted(itemCount - 1)
            loading = true
        }
    }

    fun addMoreItems(newPokemonList: List<SimplePokemon>){
        filteredPokemonList.addAll(newPokemonList)
        notifyDataSetChanged()
    }
}