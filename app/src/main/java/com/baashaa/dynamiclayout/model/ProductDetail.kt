package com.baashaa.dynamiclayout.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Stephin on 11-07-2020.
 */
@Parcelize
data class ProductDetail(
    val type: String,
    val name: String,
    val method: String,
    val submitTo: String,
    val fields: List<Field>
) : Parcelable