package cinema

import javax.print.attribute.IntegerSyntax

fun main() {

    println("Enter the number of rows:")
    val numRows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val numSeatsInRow = readLine()!!.toInt()
    val numSeats = numRows * numSeatsInRow
    val firstHalfRows = numRows / 2
    val secondHalfRows = numRows - firstHalfRows
    val cheapPrice = 8
    val goldPrice = 10

    var str1 = "";
    for (j in 0 until numSeatsInRow) str1 += "S"

    var cinemaSeats = emptyArray<CharArray>()
    for (i in 0 until numRows) cinemaSeats += str1.toCharArray()

    var userInput: Int
    var curRow = 0
    var curSeat = 0
    var priceOfTicket = 0
    var correctInput: Boolean
    var numberOfSoldTickets = 0
    var percentOfSoldTickets = 0.0
    var currentIncome = 0
    val totalIncome = when { numSeats <= 60 -> goldPrice * numSeats
                                else -> goldPrice * firstHalfRows * numSeatsInRow + cheapPrice * secondHalfRows * numSeatsInRow
    }

    do {
        showMenu()

        userInput = readLine()!!.toInt()
        when (userInput) {
            1 -> showCinemaSeats(cinemaSeats)
            2 -> {
                do {
                    correctInput = true
                    try {
                        println("Enter a row number:")
                        curRow = readLine()!!.toInt()
                        println("Enter a seat number in that row:")
                        curSeat = readLine()!!.toInt()
                        if (cinemaSeats[curRow - 1][curSeat - 1] == 'B')
                            throw Exception("That ticket has already been purchased!")
                        cinemaSeats[curRow - 1][curSeat - 1] = 'B'
                    } catch (e: IndexOutOfBoundsException) {
                        println("Wrong input!")
                        correctInput = false
                    } catch (e: Exception) {
                        println(e.message)
                        correctInput = false
                    }
                } while (!correctInput)

                priceOfTicket = when {
                    numSeats <= 60 -> goldPrice
                    curRow <= firstHalfRows -> goldPrice
                    else -> cheapPrice
                }
                println()
                println("Ticket price: \$$priceOfTicket")
                numberOfSoldTickets++;
                percentOfSoldTickets = (numberOfSoldTickets.toDouble() / numSeats.toDouble() * 100)
                currentIncome += priceOfTicket

            }
            3 -> {
                println()
                println("Number of purchased tickets: $numberOfSoldTickets")
                println("Percentage: ${String.format("%.2f",percentOfSoldTickets)}%")
                println("Current income: \$$currentIncome")
                println("Total income: \$$totalIncome")
            }
            0 -> return
        }

    } while (true)

}

fun showCinemaSeats(arr: Array<CharArray>) {
    println("Cinema:")
    print("  ")
    for (j in 0 until arr[0].size) {
        print("${j+1} ")
    }
    println()
    for (i in 0 until arr.size) {
        print("${i+1} ")
        for (j in 0 until arr[i].size) {
            print("${arr[i][j]} ")
        }
        println()
    }
    println()
}

fun showMenu() {
    println()
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
}

