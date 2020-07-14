package com.baashaa.dynamiclayout.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.baashaa.dynamiclayout.R


/**
 * Created by Stephin on 13-07-2020.
 */
class ViewPagerAdapter(private var list: List<String>?, private val mContext: Context?) :
    PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)

        val itemView: View? =
            layoutInflater.inflate(R.layout.pager_item, container, false)
        val imageView: ImageView =
            itemView?.findViewById<View>(R.id.ivImage) as ImageView
        try {

            val imageURL = list!![position]
            val resName =
                imageURL.split("\\.".toRegex())
                    .toTypedArray()[2] // remove the 'R.drawable.' prefix

            val resId: Int? =
                mContext?.resources?.getIdentifier(resName, "drawable", mContext.packageName)
            val image: Drawable? = resId?.let { mContext?.resources?.getDrawable(it) }
            imageView.setImageDrawable(image)

        } catch (nfe: NumberFormatException) {
            // Handle the condition when str is not a number.
        }
        container.addView(itemView)
        return itemView
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }

    override fun getCount(): Int {
        return list?.size!!
    }
}