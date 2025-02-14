package com.jetpack.master

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.jetpack.master.databinding.ActivityMainBinding
import com.jetpack.master.databinding.RowMainBinding

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater);
        val viewModel: MainViewModel by viewModels()
        setContentView(binding.root)

        val mainAdapter = MainListAdapter(layoutInflater)
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            adapter = mainAdapter.apply {
                submitList(viewModel.getitems())
            }
        }
    }
}

class MainListAdapter(private val inflater: LayoutInflater): ListAdapter<MainModel, MainViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(RowMainBinding.inflate(inflater ,parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       holder.bindTo(getItem(position))
    }
}

object DiffCallBack: DiffUtil.ItemCallback<MainModel>() {
    override fun areItemsTheSame(oldItem: MainModel, newItem: MainModel): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: MainModel, newItem: MainModel): Boolean = areItemsTheSame(oldItem, newItem)
}