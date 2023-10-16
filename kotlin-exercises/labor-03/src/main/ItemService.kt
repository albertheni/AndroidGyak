package main

class ItemService(private val itemRepository: ItemRepository) {
    fun selectRandomItems(numberOfItems: Int): List<Item> {
        if(numberOfItems > itemRepository.size()){
            println("Too many items requested")
            return emptyList()
        }

        val items =  mutableListOf<Item>()

        while (items.size < numberOfItems){
            val item = itemRepository.randomItem()
            if(!items.contains(item)){
                items.add(item)
            }
        }

        return items
    }
}
