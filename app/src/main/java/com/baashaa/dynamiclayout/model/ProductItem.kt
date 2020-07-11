package com.baashaa.dynamiclayout.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Stephin on 11-07-2020.
 */
@Parcelize
data class ProductItem(var list: List<ProductDetail?>? = null) : Parcelable