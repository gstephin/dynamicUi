package com.baashaa.dynamiclayout.ui.adapter

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.baashaa.dynamiclayout.R
import com.baashaa.dynamiclayout.model.Field
import kotlinx.android.synthetic.main.rv_choice_item.view.*
import kotlinx.android.synthetic.main.rv_date_item.view.*
import kotlinx.android.synthetic.main.rv_item.view.*
import kotlinx.android.synthetic.main.rv_numeric_item.view.*
import java.util.*


/**
 * Created by Stephin on 11-07-2020.
 */
class RecyclerItemAdapter(
    private val items: List<Field>,
    private val onEditTextChanged: OnEditTextChanged
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val TEXT = 0
        const val CHOICE = 1
        const val NUMERIC = 2
        const val DATE = 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val get = items[position]
        when (getItemViewType(position)) {
            TEXT -> {
                val viewHolder = holder as TextItemHolder
                viewHolder.bind(position, get.label)

            }
            NUMERIC -> {
                val viewHolder = holder as NumericItemHolder
                viewHolder.bind(position, get.label)

            }
            CHOICE -> {
                val viewHolder = holder as ChoiceItemHolder
                val str = get.options?.split("|")?.toTypedArray()
                viewHolder.bind(position, str)

            }
            DATE -> {
                val viewHolder = holder as DateItemHolder
                viewHolder.bind(position, get.label)

            }
        }

    }

    inner class TextItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val editText: EditText = itemView.etText

        fun bind(position: Int, text: String) {
            editText.hint = text
            setEditTextWatcher(position, editText)

        }


    }

    inner class ChoiceItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val editText: AutoCompleteTextView = itemView.autoCompTv

        fun bind(position: Int, list: Array<String>?) {

            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                itemView.context,
                R.layout.custom_list_item, R.id.tvListItem, list!!
            )
            editText.setAdapter(adapter)
            setEditTextWatcher(position, editText)

            editText.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, pos, _ ->
                    editText.setText(list[pos])

                }
        }
    }

    inner class NumericItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val editText: EditText = itemView.etNumeric

        fun bind(position: Int, text: String) {
            editText.hint = text
            setEditTextWatcher(position, editText)

        }
    }

    inner class DateItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val editText: EditText = itemView.etDate
        private val ivDate: ImageView = itemView.ivDate

        fun bind(position: Int, text: String) {
            editText.hint = text
            setEditTextWatcher(position, editText)
            ivDate.setOnClickListener {
                val newCalendar: Calendar = Calendar.getInstance()
                DatePickerDialog(
                    itemView.context,
                    OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        val newDate: Calendar = Calendar.getInstance()
                        newDate.set(year, monthOfYear, dayOfMonth)
                        editText.setText(
                            (dayOfMonth.toString() + "/"
                                    + (monthOfYear + 1) + "/" + year)
                        )
                    },
                    newCalendar.get(Calendar.YEAR),
                    newCalendar.get(Calendar.MONTH),
                    newCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()

            }
        }


    }

    private fun setEditTextWatcher(position: Int, editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                onEditTextChanged.onTextChanged(position, charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            "text" -> TEXT
            "choice" -> CHOICE
            "date" -> DATE
            "numeric" -> NUMERIC
            else -> TEXT
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val rootView: View?
        return when (p1) {
            TEXT -> {
                rootView =
                    LayoutInflater.from(p0.context).inflate(R.layout.rv_item, p0, false)
                TextItemHolder(rootView!!)
            }
            CHOICE -> {
                rootView =
                    LayoutInflater.from(p0.context).inflate(R.layout.rv_choice_item, p0, false)
                ChoiceItemHolder(rootView!!)
            }
            NUMERIC -> {
                rootView =
                    LayoutInflater.from(p0.context).inflate(R.layout.rv_numeric_item, p0, false)
                NumericItemHolder(rootView!!)
            }
            DATE -> {
                rootView =
                    LayoutInflater.from(p0.context).inflate(R.layout.rv_date_item, p0, false)
                DateItemHolder(rootView!!)
            }

            else -> {
                rootView = LayoutInflater.from(p0.context).inflate(
                    R.layout.rv_item,
                    p0,
                    false
                )
                EmptyViewHolder(rootView!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnEditTextChanged {
        fun onTextChanged(position: Int, charSeq: String?)
    }

}


