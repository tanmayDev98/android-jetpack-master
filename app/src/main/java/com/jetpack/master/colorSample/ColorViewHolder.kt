package com.jetpack.master.colorSample

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.master.R
import com.jetpack.master.databinding.RowColorBinding

class ColorViewHolder(private val rowBinding: RowColorBinding): RecyclerView.ViewHolder(rowBinding.root) {
    private var color: Int = 0x7FFFFFFF
    init {
        rowBinding.root.setOnClickListener(this::showSwatch)
 //     rowBinding.root.setOnClickListener {
//          Toast.makeText(
//              rowBinding.tvColorValue.context,
//              rowBinding.tvColorValue.text,
//              Toast.LENGTH_LONG
//          ).show()
    }

    private fun showSwatch(v: View) {
        val context = v.context
        context.startActivity(Intent(context, SwatchActivity::class.java)
            .putExtra(EXTRA_COLOR, color)
        )
    }

    fun bindTo(color: Int) {
        this.color = color
        rowBinding.tvColorValue.text = rowBinding.tvColorValue.context.getString(R.string.color_template, color)
        rowBinding.vColor.setBackgroundColor(color)
    }
}