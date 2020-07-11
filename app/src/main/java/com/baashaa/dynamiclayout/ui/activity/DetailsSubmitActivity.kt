package com.baashaa.dynamiclayout.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.baashaa.dynamiclayout.R
import com.baashaa.dynamiclayout.contsants.Constants
import com.baashaa.dynamiclayout.model.ItemPostData
import kotlinx.android.synthetic.main.activity_submit.*

/**
 * Created by Stephin on 11-07-2020.
 */
class DetailsSubmitActivity : AppCompatActivity() {

    private var itemPostData: ItemPostData? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        itemPostData = intent.getParcelableExtra(Constants.INTENT_DATA)
        val name = intent.getStringExtra(Constants.INTENT_TITLE)
        tvTitle.text = name
        if (!(itemPostData?.name.isNullOrEmpty())) {
            tvName.text = "Item Name : " + itemPostData?.name
            tvName.visibility = View.VISIBLE
        }
        if (!(itemPostData?.category.isNullOrEmpty())) {
            tvCategory.text = "Item category : " + itemPostData?.category
            tvCategory.visibility = View.VISIBLE
        }
        if (!(itemPostData?.date.isNullOrEmpty())) {
            tvDate.text = "Item date : " + itemPostData?.date
            tvDate.visibility = View.VISIBLE
        }
        if ((itemPostData?.quantity!! > 0)) {
            tvQuantity.visibility = View.VISIBLE
            tvQuantity.text = "Item quantity : " + itemPostData?.quantity
        }
        if ((itemPostData?.itemCode!! > 0)) {
            tvQuantity.visibility = View.VISIBLE
            tvQuantity.text = "Item Code : " + itemPostData?.itemCode
        }
    }
}