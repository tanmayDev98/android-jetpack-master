package com.jetpack.master.toDoSample.repository

import java.time.Instant

data class ToDoModel(
    val id: String,
    val title: String,
    val description: String,
    val createdOn: Instant = Instant.now()
) {
}