package com.jetpack.master.toDoSample.repository

class ToDoRepository {
    private val items = listOf(
        ToDoModel(
            id = "1",
            title = "Buy a book",
            description = "We will buy this book"
        ),
        ToDoModel(
            id = "2",
            title = "Look into future prospects",
            description = "AI driven applications look promising"
        ),
        ToDoModel(
            id = "3",
            title = "Travel all over the world",
            description = "Pan a trip to europe this year"
        )
    ).associateBy { it.id }

    fun getitems() : List<ToDoModel> = items.values.toList()

    fun findItemById(id: String) = items[id]
}