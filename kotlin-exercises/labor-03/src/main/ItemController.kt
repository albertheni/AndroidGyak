package main

class ItemController(private val itemService: ItemService) {
    fun quiz(numberOfItems: Int){
        val items = itemService.selectRandomItems(numberOfItems)

        for(i in items){
            println("${i.question}\n1.)${i.answers[0]}   2.)${i.answers[1]}   3.)${i.answers[2]}   4.)${i.answers[3]}")
            val answer = readLine()!!.toInt()
            if (answer == i.correctAnswer){
                println("Correct answer")
            }
            else {
                println("Wrong answer")
            }
        }
    }
}

