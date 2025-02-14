package com.jetpack.master.toDoSample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.jetpack.master.databinding.ActivityTodoBinding
import com.jetpack.master.toDoSample.list.ListFragment

class ToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                add(android.R.id.content, ListFragment())
            }
        }
    }
}