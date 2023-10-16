package main

import main.IDictionary
import java.time.LocalDate


fun main(){
    //Problem 1

   /* val dict: IDictionary = ListDictionary()
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }
    */

    //Test TreeSet Dictionary with dictionary provider
    /*val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.TREE_SET)
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }*/

    //Test HashSet Dictionary with dictionary provider
    /*val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.HASH_SET)
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }*/

    //Problem 2
    /*
    //Test string.monogram
    println("John Smith".monogram())

    //Test List<String>.joinToStr
    val list = listOf("apple", "pear", "melon")
    println(list.joinToStr("#"))

    //Test List<String>.longest
    val list2 = listOf("apple", "pear","strawberry", "melon")
    println(list2.longest())
*/
    //Pronblem 3
    //Test isLapYear
    val date = Date(2000, 2, 29)
    println(date.isLeapYear())

    //Test isValid
    val dates = mutableListOf<Date>()
    while (dates.size<10){
        val year = (1 .. LocalDate.now().year).random()
        val month = (1 .. 12).random()
        val day = (1 ..31 ).random()
        val date2 = Date(year, month, day)
        if(date2.isValid()) dates.add(date2)
        else {println(date2)}
    }
    println("Valis dates--------------------------")
    dates.forEach { println(it) }

    dates.sort()
    println("Sorted dates--------------------------")
    dates.forEach { println(it) }

    dates.reverse()
    println("Reversed dates------------------------")
    dates.forEach { println(it) }

    dates.sortBy { it.day }
    println("Sorted by day--------------------------")
    dates.forEach { println(it) }

}

fun String.monogram(): String {
    return this.split(" ").map { it.first() }.joinToString("")
}
//Define a compact extension function that returns the elements of a strings’ list joined by
//a given separator!
fun List<String>.joinToStr(separator: String): String {
    return this.joinToString(separator)
}

//Define a compact extension function for a strings’ list that returns the longest string!
fun List<String>.longest(): String {
    return this.maxBy { it.length }
}

