package main

import java.util.Base64
import kotlin.random.Random


fun main() {
    //1.feladat
    println("1.feladat");
    val a = 2
    val b = 5
    val sum = a + b
    println("sum = $sum")
    println ("sum = ${2+5}")
    println("-----------------");

    //2.feladat
    println("2.feladat");
    val arr = listOf("Monday","Tuesday","Wednesday","Thursday","Friday",
            "Saturday","Sunday")
    for(day in arr){
        println(day)
    }
    println("-----------------");
    // day:String in arr
    arr.filter{
        it.startsWith("T")
    }.forEach{
        println(it)
    }
    println("-----------------");

    arr.filter{
        it.contains("e")
    }.forEach{
        println(it)
    }
    println("-----------------");

    arr.filter{
        it.length==6
    }.forEach{
        println(it)
    }
    println("-----------------");

    //3.feladat
    println("3.feladat");
    for ( i in 1..100){
        if (isPrime(i)){
            println(i)
        }

    }
    println("-----------------");

    //4.feladat
    println("4.feladat");
    val name = encode("Henrietta");
    println(name);
    println(decode(name));
    println("-----------------");

    ///5.feladat
    println("5.feladat");
    val list = listOf(1,2,36,4,5,6,7,8,9,10,11,12,13,14,15);
    isEven(list);
    println("-----------------");

    //6.feladat
    println("6.feladat");
    val dubledNumbers = list.map{it*2}
    println(dubledNumbers);
    println("-----------------");
    val capitalizedDays = arr.map { it.toUpperCase() }
    //println(capitalizedDays);
    capitalizedDays.forEach{println(it)}
    println("-----------------");
    val firstCharacter = arr.map{it.first().toUpperCase().toString()}
    firstCharacter.forEach{ println(it) }
    println("-----------------");
    val lengthOfDays = arr.map{it.length}
    println(lengthOfDays);
    println("-----------------");
    val averageLength = lengthOfDays.average();
    println(averageLength);
    println("-----------------");

    //7.feladat
    println("7.feladat");
    val mutableArr = arr.toMutableList()
    mutableArr.removeAll{it.contains('n',ignoreCase = true)}
    println(mutableArr);
    println("-----------------");
    val indexedArr = mutableArr.withIndex();
    for ((index,day) in indexedArr){
        println("Item at $index is $day")
    }
    println("-----------------");
    val sortedArr = mutableArr.sorted()
    println(sortedArr);
    println("-----------------");

    //8.feladat
    println("8.feladat");
    val randomNumbers = Array(10){Random.nextInt(101)}
    randomNumbers.forEach { println(it) }
    println("-----------------");
    val sortedNumbers = randomNumbers.sorted()
    sortedNumbers.forEach { println(it) }
    println("-----------------");
    val containsEven = randomNumbers.any { it % 2 == 0 }
    if (containsEven){
        println("yes")
    }
    else println("no")
    println("-----------------");
    val allEven = randomNumbers.all { it % 2 == 0 }
    if (allEven){
        println("all")
    }
    else println("not all")
    println("-----------------");
    val sum2 = randomNumbers.sum();
    val avg = sum2.toDouble() / randomNumbers.size;
    randomNumbers.forEach { println(it) }

    println("Average: $avg");



}

fun isPrime ( number:Int ) :Boolean{
    if (number <= 1) {
        return false
    }
    for (i in 2 until number) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}

fun isEvenNumber(a:Int) = a%2 == 0 //compact function
fun isEven(numbers: List<Int>) {
    val evenNumbers = numbers.filter { it % 2 == 0 }
    evenNumbers.forEach { println(it) }
}

fun encode(s:String) :String
{
    return Base64.getEncoder().encodeToString(s.toByteArray())
}

fun decode (s:String) :String{
    return String(Base64.getDecoder().decode(s))
}