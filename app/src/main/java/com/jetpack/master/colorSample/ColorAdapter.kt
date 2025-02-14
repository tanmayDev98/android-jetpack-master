package com.jetpack.master.colorSample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jetpack.master.databinding.RowColorBinding

class ColorAdapter(private val inflater: LayoutInflater): ListAdapter<Int, ColorViewHolder>(
    DiffCallBack
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder =
        ColorViewHolder(RowColorBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}
private object DiffCallBack: DiffUtil.ItemCallback<Int>()  {
    override fun areItemsTheSame(oldColor: Int, newColor: Int): Boolean =
        oldColor == newColor

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}