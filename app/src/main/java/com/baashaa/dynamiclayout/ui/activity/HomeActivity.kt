package com.baashaa.dynamiclayout.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baashaa.dynamiclayout.R
import com.baashaa.dynamiclayout.model.HomeModel
import com.baashaa.dynamiclayout.ui.adapter.RecyclerHeaderAdapter
import com.baashaa.dynamiclayout.ui.adapter.ViewPagerAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonFileString = getJsonDataFromAsset(applicationContext, "home.json")

        val gson = Gson()
        val listPersonType = object : TypeToken<HomeModel>() {}.type

        val homeModel: HomeModel = gson.fromJson(jsonFileString, listPersonType)

        val adapter = ViewPagerAdapter(homeModel.banners?.links, this)
        pager.adapter = adapter
        indicator.setViewPager(pager)
        adapter.registerDataSetObserver(indicator.dataSetObserver)


        val adapterRecycler = RecyclerHeaderAdapter(homeModel.headers)
        rvItems?.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapterRecycler
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}