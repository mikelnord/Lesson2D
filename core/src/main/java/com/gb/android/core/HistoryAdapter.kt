package com.gb.android.core

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.android.core.databinding.HistoryRecyclerviewItemBinding
import com.gb.android.model.HistoryEntity


class HistoryAdapter(private var data: List<HistoryEntity>) :
    RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecyclerItemViewHolder(
            HistoryRecyclerviewItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val bind: HistoryRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun bind(historyEntity: HistoryEntity) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                bind.headerTextviewRecyclerItem.text = historyEntity.word
                bind.descriptionTextviewRecyclerItem.text = historyEntity.description
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<HistoryEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

}