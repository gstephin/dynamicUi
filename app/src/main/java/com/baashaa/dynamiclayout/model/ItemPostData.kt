package com.baashaa.dynamiclayout.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Stephin on 11-07-2020.
 */
@Parcelize
data class ItemPostData(
    var name: String?,
    var date: String?,
    var quantity: Int? = 0,
    var category: String?,
    var itemCode: Int?
) : Parcelable