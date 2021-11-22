package com.example.picnic_android_maryambehzi.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.picnic_android_maryambehzi.databinding.GridViewItemBinding
import com.example.picnic_android_maryambehzi.network.GifModel

class PhotoGridAdapter ( val onClickListener: OnClickListener ) :
    ListAdapter<GifModel, PhotoGridAdapter.GifViewHolder>(DiffCallback) {

    class GifViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: GifModel) {
            binding.gif = marsProperty
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<GifModel>() {
        override fun areItemsTheSame(oldItem: GifModel, newItem: GifModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GifModel, newItem: GifModel): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): GifViewHolder {
        return GifViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifModel = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(gifModel)
        }
        holder.bind(gifModel)
    }


    class OnClickListener(val clickListener: (marsProperty:GifModel) -> Unit) {
        fun onClick(marsProperty:GifModel) = clickListener(marsProperty)
    }
}
