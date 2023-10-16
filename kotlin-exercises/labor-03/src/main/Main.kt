package main

fun main() {
    val repo = ItemRepository
    val service = ItemService(repo)
    val itemController = ItemController(service)

    itemController.quiz(3)
}