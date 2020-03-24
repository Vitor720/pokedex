package com.ddapps.pokedex.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddapps.pokedex.R
import com.ddapps.pokedex.common.domain.models.ui.Abilities
import com.ddapps.pokedex.databinding.RowAbilityBinding
import com.ddapps.pokedex.utils.IOnClickAbilityListener

class AbilityAdapter(private val abilitiesList: List<Abilities>, private val clickListener: IOnClickAbilityListener): RecyclerView.Adapter<AbilityAdapter.AbilityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val view: RowAbilityBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_ability, parent, false)
        return AbilityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return abilitiesList.size
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val row = abilitiesList[position]

        holder.binding.ability.text = row.name?.replace("-", " ")?.capitalize()

        holder.binding.rowLayout.setOnClickListener {
            clickListener.onAbilityClick(row.url ?: "")
        }
    }


    inner class AbilityViewHolder internal constructor(val binding: RowAbilityBinding): RecyclerView.ViewHolder(binding.root)
}