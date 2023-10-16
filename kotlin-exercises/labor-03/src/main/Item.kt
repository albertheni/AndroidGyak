package main

data class Item(
        val question : String,
        val answers: MutableList<String>,
        val correctAnswer: Int
)
