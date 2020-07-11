package com.baashaa.dynamiclayout.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.baashaa.dynamiclayout.R
import com.baashaa.dynamiclayout.contsants.Constants.INTENT_DATA
import com.baashaa.dynamiclayout.contsants.Constants.INTENT_TITLE
import com.baashaa.dynamiclayout.model.ItemPostData
import com.baashaa.dynamiclayout.model.ProductDetail
import com.baashaa.dynamiclayout.ui.adapter.RecyclerItemAdapter
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 * Created by Stephin on 11-07-2020.
 */
class ItemDetailActivity : AppCompatActivity(), RecyclerItemAdapter.OnEditTextChanged {
    private var productItem: ProductDetail? = null
    private var itemPostData: ItemPostData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        productItem = intent.getParcelableExtra(INTENT_DATA)

        val adapter = productItem?.fields?.let {
            RecyclerItemAdapter(
                it,
                this
            )
        }
        rvItems.layoutManager = LinearLayoutManager(this)
        rvItems.adapter = adapter
        itemPostData = ItemPostData(null, null, null, null, 0)
        itemPostData?.quantity = 0
        btnSubmit.setOnClickListener {
            val intent = Intent(this, DetailsSubmitActivity::class.java)
            intent.putExtra(INTENT_DATA, itemPostData)
            intent.putExtra(INTENT_TITLE, productItem?.name)
            startActivity(intent)
        }
    }

    override fun onTextChanged(position: Int, charSeq: String?) {
        when (productItem?.fields?.get(position)?.type) {
            "text" -> itemPostData?.name = charSeq
            "choice" -> itemPostData?.category = charSeq
            "date" -> itemPostData?.date = charSeq
            "numeric" -> {
                if (productItem?.fields?.get(position)?.label.equals("Item code"))
                    itemPostData?.itemCode = charSeq?.toInt()
                else
                    itemPostData?.quantity = charSeq?.toInt()
            }
            else -> itemPostData?.name = charSeq
        }
    }
}