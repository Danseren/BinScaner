package ru.sample.store.binscaner.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sample.store.binscaner.Bin
import ru.sample.store.binscaner.databinding.CardBinHistoryListBinding

class RecyclerBinList (private val listData: List<Bin>, private val callback: OnItemClick) :
    RecyclerView.Adapter<RecyclerBinList.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding = CardBinHistoryListBinding.inflate(LayoutInflater.from(parent.context))
        return DetailsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(bin: Bin) {
            val binding = CardBinHistoryListBinding.bind(itemView)
            binding.tvTitle.text = bin.number
            binding.root.setOnClickListener {
                callback.onItemClick(bin)
            }
        }
    }
}