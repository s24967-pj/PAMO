package com.example.kotlinexercises

import kotlin.math.PI
import kotlin.random.Random

/**
 * EXERCISE 1
 *
 * You have a set of protocols supported by your server. A user requests to use a particular protocol.
 * Complete the program to check whether the requested protocol is supported or not (isSupported must be a Boolean value).
 */
fun exercise1() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("Support for $requested: $isSupported")
}

/**
 * EXERCISE 2
 *
 * Create a simple game where you win if throwing two dice results in the same number. Use if to print You win :) if the dice match or You lose :( otherwise.
 */
fun exercise2() {
    val firstResult = Random.nextInt(6)
    val secondResult = Random.nextInt(6)
    if (firstResult == secondResult)
        println("You win :)")
    else
        println("You lose :(")
}

/**
 * EXERCISE 3
 *
Using a when expression, update the following program so that it prints the corresponding actions when you input the names of game console buttons.
Button Action
A Yes
B No
X Menu
Y Nothing
Other There is no such button
 */
fun exercise3() {
    val button = "A"
    println(
        when (button) {
            "A" -> "Yes"
            "B" -> "No"
            "X" -> "Menu"
            "Y" -> "Nothing"
            else -> "There is no such button"
        }
    )
}

/**
 * EXERCISE 4
 *
 * You have a list of "green" numbers and a list of "red" numbers. Complete the code to print how many numbers there are in total.
 */
fun exercise4() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    val totalNumbers = greenNumbers.size + redNumbers.size
    println("Total numbers: $totalNumbers")
}

/**
 * EXERCISE 5
 *
 * Explicitly declare the correct type for each variable
 */
fun exercise5() {
        val a: Int = 1000
        val b: String = "log message"
        val c: Double = 3.14
        val d: Long = 100_000_000_000_000
        val e: Boolean = false
        val f: Char = '\n'
}

/**
 * EXERCISE 6
 *
 * Define a map that relates integer numbers from 1 to 3 to their corresponding spelling. Use this map to spell the given number.
 */
fun exercise6() {
        val number2word = mapOf(1 to "one", 2 to "two", 3 to "three")
        val n = 2
        println("$n is spelt as '${number2word[n]}'")
}

/**
 * EXERCISE 7
 *
 * Write a program that simulates the Fizz buzz game. Your task is to print numbers from 1 to 100 incrementally, replacing any number divisible by three with the word
 * "fizz", and any number divisible by five with the word "buzz". Any number divisible by both 3 and 5 must be replaced with the word "fizzbuzz".
 * Hint 1
 * Use a for loop to count numbers and a when expression to decide what to print at each step.
 * Hint 2
 * Use the modulo operator (%) to return the remainder of a number being divided. Use the equality operator (==) to check if the remainder equals zero.
 */
fun exercise7() {
    for (number in 1..100) {
        println(
            when {
                number % 15 == 0 -> "fizzbuzz"
                number % 3 == 0 -> "fizz"
                number % 5 == 0 -> "buzz"
                else -> "$number"
            }
        )
    }
}

/**
 * EXERCISE 8
 *
 * You have a list of words. Use for and if to print only the words that start with the letter l.
 * Hint
 * Use the .startsWith() function for String type.
 */
fun exercise8() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")
    for (w in words) {
        if (w.startsWith("l"))
            println(w)
    }
}

/**
 * EXERCISE 9
 *
 * Write a function called circleArea that takes the radius of a circle in integer format as a parameter and outputs the area of that circle.
 */
fun exercise9() {
    fun circleArea(radius: Int): Double {
        return PI * radius * radius
    }
    fun main() {
        println(circleArea(2)) // 12.566370614359172
    }
}

/**
 * EXERCISE 10
 *
 * You have a list of actions supported by a web service, a common prefix for all requests, and an ID of a particular resource. To request an action title over the
 * resource with ID: 5, you need to create the following URL: https://example.com/book-info/5/title. Use a lambda expression to create a list of URLs from the list of
 * actions.
 */
fun exercise10() {
        val actions = listOf("title", "year", "author")
        val prefix = "https://example.com/book-info"
        val id = 5
        val urls = actions.map { action -> "$prefix/$id/$action" }
        println(urls)
}

/**
 * EXERCISE 11
 *
 * Define a data class Employee with two properties: one for a name, and another for a salary. Make sure that the property for salary is mutable, otherwise you won’t
 * get a salary boost at the end of the year! The main function demonstrates how you can use this data class.
 */
data class Employee(val name: String, var salary: Int)
fun exercise11() {
        val emp = Employee("Mary", 20)
        println(emp)
        emp.salary += 10
        println(emp)
}

/**
 * EXERCISE 12
 *
 * You have a function that translates a time interval given in hours, minutes, and seconds into seconds. In most cases, you need to pass only one or two function
 * parameters while the rest are equal to 0. Improve the function and the code that calls it by using default parameter values and named arguments so that the code is
 * easier to read.
 */
fun exercise12(hours: Int = 0, minutes: Int = 0, seconds: Int = 0): Int {
    return ((hours * 60) + minutes) * 60 + seconds
}




fun main()
{
    println("EXERCISE 1")
    exercise1()

    println("EXERCISE 2")
    exercise2()

    println("EXERCISE 3")
    exercise3()

    println("EXERCISE 4")
    exercise4()

    println("EXERCISE 5")
    exercise5()

    println("EXERCISE 6")
    exercise6()

    println("EXERCISE 7")
    exercise7()

    println("EXERCISE 8")
    exercise8()

    println("EXERCISE 9")
    exercise9()

    println("EXERCISE 10")
    exercise10()

    println("EXERCISE 11")

    println("EXERCISE 12")
    println(exercise12(hours = 1, minutes = 20, seconds = 15))
    println(exercise12(minutes = 1, seconds = 25))
    println(exercise12(hours = 2))
    println(exercise12(minutes = 10))
    println(exercise12(hours = 1, seconds = 1))
}