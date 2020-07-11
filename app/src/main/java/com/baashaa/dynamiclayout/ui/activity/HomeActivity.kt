package com.baashaa.dynamiclayout.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.baashaa.dynamiclayout.R
import com.baashaa.dynamiclayout.contsants.Constants.INTENT_DATA
import com.baashaa.dynamiclayout.model.MyDeserializer
import com.baashaa.dynamiclayout.model.ProductItem
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.IOException


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonFileString = getJsonDataFromAsset(applicationContext, "dynlayout.json")

        val gson: Gson = GsonBuilder()
            .registerTypeAdapter(ProductItem::class.java, MyDeserializer())
            .create()

        val productItem: ProductItem = gson.fromJson(jsonFileString, ProductItem::class.java)
        val items = arrayListOf<String>()

        productItem.list?.forEachIndexed { _, item ->
            item?.name?.let { items.add(it) }
        }
        val editText: AutoCompleteTextView = findViewById(R.id.actv)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.custom_list_item, R.id.tvListItem, items
        )
        editText.setAdapter(adapter)
        editText.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, id ->
                val intent = Intent(this, ItemDetailActivity::class.java)
                intent.putExtra(INTENT_DATA, productItem.list?.get(position))
                startActivity(intent)
            }

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