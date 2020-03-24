package com.ddapps.pokedex.ui.fragments.tabs


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddapps.pokedex.R
import com.ddapps.pokedex.common.adapter.AbilityAdapter
import com.ddapps.pokedex.common.domain.models.ui.Abilities
import com.ddapps.pokedex.common.domain.models.ui.FullAbility
import com.ddapps.pokedex.common.domain.models.ui.Pokemon
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.data.remote.Status
import com.ddapps.pokedex.databinding.FragmentAbilitiesBinding
import com.ddapps.pokedex.ui.HomeViewModel
import com.ddapps.pokedex.utils.IOnClickAbilityListener
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber


class AbilitiesFragment : Fragment(), IOnClickAbilityListener {

    private var binding: FragmentAbilitiesBinding? = null
    private val viewModel: HomeViewModel by sharedViewModel()
    private lateinit var abilityObservable: Observer<FullAbility?>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_abilities, container, false)

        val observer = Observer<Resource<Pokemon>>{
            when(it.status){
                Status.SUCCESS -> {loadRecycler(it.data?.abilities!!)}
                Status.LOADING -> {}
                Status.ERROR   -> {}
            }
        }

        viewModel.getPokemonDisplay().observe(this, observer)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.getPokemonDisplay().removeObservers(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        abilityObservable = Observer<FullAbility?> {
            showConfirmDialog(it?.effect, it?.name?.replace("-", " "))
        }
    }

    private fun loadRecycler(abilitiesList: List<Abilities>){
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.abilityRecycler?.layoutManager = layoutManager
        val adapter = AbilityAdapter(abilitiesList, this)
        binding?.abilityRecycler?.adapter = adapter
    }


    override fun onAbilityClick(url: String) {
        val id = url.removePrefix("https://pokeapi.co/api/v2/ability/").removeSuffix("/")
        viewModel.getAbility(id.toInt()).observe(this, abilityObservable)
    }

    private fun showConfirmDialog(description: String?, name: String?) {
        if (description != null && name != null) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context!!)
            builder.setTitle(name.capitalize())
            builder.setMessage(description)
            builder.setPositiveButton("close") { dialogInterface, i ->
                Snackbar.make(
                    binding?.root!!,
                    "",
                    Snackbar.LENGTH_SHORT
                )
            }
            builder.show()
        }
    }


}
