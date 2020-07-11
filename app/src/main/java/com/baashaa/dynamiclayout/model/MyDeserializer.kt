package com.baashaa.dynamiclayout.model

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

class MyDeserializer : JsonDeserializer<ProductItem> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        je: JsonElement,
        type: Type,
        jdc: JsonDeserializationContext
    ): ProductItem {
        val list: MutableList<ProductDetail> =
            ArrayList()
        val array = je.asJsonObject.getAsJsonArray("dynlayouts")
        for (je2 in array.asJsonArray) {
            val set =
                je2.asJsonObject.entrySet()
            for ((_, je3) in set) {
                val mup =
                    Gson().fromJson(je3, ProductDetail::class.java)
                list.add(mup)
            }
        }
        val mp = ProductItem()
        mp.list = list
        return mp
    }
}