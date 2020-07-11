package com.baashaa.dynamiclayout.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Stephin on 11-07-2020.
 */
@Parcelize
data class Field(
    val name: String,
    val label: String,
    val type: String,
    val required: String,
    val options: String?
) : Parcelable