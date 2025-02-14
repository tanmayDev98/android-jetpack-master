package com.jetpack.master
import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jetpack.master.databinding.RowMainBinding

class MainViewHolder(val binding: RowMainBinding): ViewHolder(binding.root) {

    private fun showActivity(v: View, activity: Activity) {
        val context = v.context
        context.startActivity(Intent(context,activity::class.java))
    }

    fun bindTo(model: MainModel) {
        binding.apply {
            tvName.text = model.name
            root.setOnClickListener {
                showActivity(it, model.activity)
            }
        }
    }
}