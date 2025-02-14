package com.jetpack.master.toDoSample.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.master.databinding.FragmentTodolistBinding
import com.jetpack.master.toDoSample.details.DetailsFragment
import com.jetpack.master.toDoSample.repository.ToDoModel

class ListFragment: Fragment() {
    private var binding: FragmentTodolistBinding? = null
    private val vm : ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTodolistBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListViewAdapter(layoutInflater, onRowClick = :: toDisplay)
        binding?.rvTodolist?.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
        adapter.submitList(vm.items)
    }

    private fun toDisplay(model: ToDoModel) {
        parentFragmentManager.commit {
            replace(android.R.id.content,  DetailsFragment.newInstance(model.id))
            addToBackStack(null)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}