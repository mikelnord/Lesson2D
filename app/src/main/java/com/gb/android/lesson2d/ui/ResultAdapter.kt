package com.gb.android.lesson2d.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.android.lesson2d.databinding.ResultRecyclerviewItemBinding
import com.gb.android.lesson2d.model.DataModel

class ResultAdapter(
    private var data: List<DataModel>,
    private val clickListener: ClickListener
) :
    RecyclerView.Adapter<ResultAdapter.RecyclerItemViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecyclerItemViewHolder(
            ResultRecyclerviewItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val bind: ResultRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun bind(data: DataModel, clickListener: ClickListener) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                bind.headerTextviewRecyclerItem.text = data.text
                bind.descriptionTextviewRecyclerItem.text =
                    data.meanings?.get(0)?.translation?.translation
                bind.itemLinearLayout.setOnClickListener {
                    clickListener.onClick(data)
                }
            }
        }
    }

}

class ClickListener(val clickListener: (dataModel: DataModel) -> Unit) {
    fun onClick(dataModel: DataModel) = clickListener(dataModel)
}

