package com.osinit.internship.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.osinit.internship.R
import com.osinit.internship.data.CurrencyInfo
import com.osinit.internship.databinding.ItemCurrencyBinding
import com.osinit.internship.util.replaceToDot

class CurrenciesAdapter(private var currencies: List<CurrencyInfo>) :
    ListAdapter<CurrencyInfo, CurrenciesAdapter.CurrenciesViewHolder>(CurrencyDiffCallback) {

    var listener: ((CurrencyInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return CurrenciesViewHolder(binding)
    }

    override fun onViewRecycled(holder: CurrenciesViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(currencies[position])
    }

    override fun getItemCount(): Int = currencies.size

    inner class CurrenciesViewHolder(
        private val binding: ItemCurrencyBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currencyInfo: CurrencyInfo) {
            binding.textName.text = currencyInfo.charCode
            binding.textValue.text =
                binding.root.context.getString(R.string.symbol, currencyInfo.value).replaceToDot()
            itemView.setOnClickListener {
                listener?.invoke(currencyInfo)
            }
        }

        fun unbind() {
            itemView.setOnClickListener(null)
        }
    }
}

object CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyInfo>() {
    override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
        return oldItem == newItem
    }
}