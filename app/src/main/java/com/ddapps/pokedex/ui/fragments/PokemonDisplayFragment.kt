package com.ddapps.pokedex.ui.fragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.ddapps.pokedex.R
import com.ddapps.pokedex.common.adapter.ImageSliderAdapter
import com.ddapps.pokedex.common.adapter.ViewPagerAdapter
import com.ddapps.pokedex.common.domain.models.ui.Pokemon
import com.ddapps.pokedex.data.remote.Resource
import com.ddapps.pokedex.data.remote.Status
import com.ddapps.pokedex.databinding.PokemonDisplayFragmentBinding
import com.ddapps.pokedex.ui.HomeViewModel
import com.ddapps.pokedex.utils.PokemonColorUtil
import com.ddapps.pokedex.utils.gone
import com.ddapps.pokedex.utils.show
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.pokemon_display_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class PokemonDisplayFragment : Fragment() {

    private val viewModel: HomeViewModel by sharedViewModel()
    private var binding: PokemonDisplayFragmentBinding? = null
    private var adapterImageSlider: ImageSliderAdapter? = null
    private var imageList = mutableListOf<String>()
    private lateinit var typeObservable: Observer<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_display_fragment, container,false)

        val observer = Observer<Resource<Pokemon>> {
            when (it.status) {
                Status.SUCCESS -> {

                    val pokemon = it.data!!
                    binding?.pokemon = pokemon

                    setColor(it)

                    setUpTypes(it.data)

                    binding?.pokemonType1?.setOnClickListener {
                        viewModel.getSamePokemonTypeList(getPokemonTypeID(it as TextView, pokemon)).observe(this, typeObservable)
                    }

                    binding?.pokemonType2?.setOnClickListener {
                        viewModel.getSamePokemonTypeList(getPokemonTypeID(it as TextView, pokemon)).observe(this, typeObservable)
                    }

                    binding?.pokemonType3?.setOnClickListener {
                        viewModel.getSamePokemonTypeList(getPokemonTypeID(it as TextView, pokemon)).observe(this, typeObservable)
                    }

                    imageList.add(it.data.getPokemonID())
                    imageList.addAll(it.data.images.filterNotNull())
                    setUpAdapterImagem(imageList)

                    setUpViewPager()
                    binding?.loading!!.gone()
                    binding?.nestedScrollLayout!!.show()
                    binding?.appBar!!.show()
                }
                Status.ERROR -> {showDialog("Error", it.message.toString())}
                Status.LOADING -> {
                    binding?.loading!!.show()
                }
            }
        }

        viewModel.getPokemonDisplay().observe(this, observer)

        setHasOptionsMenu(true)
        return binding?.root
    }

    private fun setColor(it: Resource<Pokemon>) {
        val color = PokemonColorUtil(view!!.context).getPokemonColor(binding?.pokemon!!.getPokemonType())
        app_bar.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        toolbar_layout.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        activity?.window?.statusBarColor = PokemonColorUtil(view!!.context).getPokemonColor(it.data!!.getPokemonType())
    }

    private fun setUpTypes(data: Pokemon) {
        data.type?.getOrNull(0).let { firstType ->
            binding!!.pokemonType1.text = firstType?.name
            binding!!.pokemonType1.isVisible = firstType != null
        }

        data.type?.getOrNull(1).let { secondType ->
            binding?.pokemonType2?.text = secondType?.name
            binding?.pokemonType2!!.isVisible = secondType != null
        }

        data.type?.getOrNull(2).let { thirdType ->
            binding?.pokemonType3?.text = thirdType?.name
            binding?.pokemonType3?.isVisible = thirdType != null
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        typeObservable = Observer<String> {
            showDialog("Pokemon's Same Type List" ,it)
        }

    }

    private fun getPokemonTypeID(view: TextView, pokemon: Pokemon): Int{
        val typeName = view.text
        val typeUrl = pokemon.type!!.first() { it.name == typeName }
        val typeID = typeUrl.url.removePrefix("https://pokeapi.co/api/v2/type/").removeSuffix("/")
        return typeID.toInt()
    }

    private fun setUpViewPager() {
        val viewPager = binding?.infoPager
        val tabs = tabs
        viewPager?.adapter = ViewPagerAdapter(childFragmentManager, requireContext())
        tabs.setupWithViewPager(viewPager)
    }

    private fun setUpAdapterImagem(listOfImages: List<String>) {
        val imagePager = binding?.pokemonImageViewPager
        adapterImageSlider = ImageSliderAdapter(activity!!, listOfImages)
        imagePager?.adapter = adapterImageSlider

        imagePager?.currentItem = 0
        imagePager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(pos: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(pos: Int) {
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
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
}
