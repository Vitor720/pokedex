package com.ddapps.pokedex.common.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.ddapps.pokedex.R
import com.ddapps.pokedex.utils.load
import kotlinx.android.synthetic.main.item_slider_image.view.*

class ImageSliderAdapter (private val act: Activity, private var items: List<String>?) : PagerAdapter() {

    override fun getCount(): Int {
        return this.items!!.size
    }

    private var onItemClickListener: OnItemClickListener? = null

    private interface OnItemClickListener {
        fun onItemClick(view: View)
    }

    private fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

//    fun getItem(pos: Int): Image {
//        return items.get(pos)
//    }

    fun setItems(items: MutableList<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val url = items!![position]
        val inflater = act.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflatedView = inflater.inflate(R.layout.item_slider_image, container, false)

        val image = inflatedView.image
        val lyt_parent = inflatedView.lyt_parent


        image.load(url)

        lyt_parent.setOnClickListener { view ->
            if (onItemClickListener != null) {
                onItemClickListener!!.onItemClick(view)
            }
        }

        (container as ViewPager).addView(inflatedView)

        return inflatedView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as RelativeLayout)

    }

}
