package com.ddapps.pokedex.common.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ddapps.pokedex.R
import com.ddapps.pokedex.ui.fragments.tabs.AbilitiesFragment
import com.ddapps.pokedex.ui.fragments.tabs.EvolutionFragment
import com.ddapps.pokedex.ui.fragments.tabs.StatsFragment


class ViewPagerAdapter(supportFragmentManager: FragmentManager, context: Context)
    : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val ctor: () -> Fragment)

    private val pages = listOf(
        Page(
            context.getString(R.string.pokemon_stats)
        ) { StatsFragment() },
        Page(
            context.getString(R.string.pokemon_evolution)
        ) { EvolutionFragment() },
        Page(
            context.getString(R.string.pokemon_abilities)
        ) { AbilitiesFragment() }
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].ctor()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}
