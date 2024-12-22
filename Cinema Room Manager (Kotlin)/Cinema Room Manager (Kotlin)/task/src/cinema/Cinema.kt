package cinema

fun printInstruction() {
    println()
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
}

fun totalPossibleIncome(totalRow: Int, seatPerRow: Int): Int {
    val totalSeat = totalRow * seatPerRow
    var totalPrice = 0
    if (totalSeat <= 60) {
        totalPrice = 10 * totalSeat
    } else {
        val frontHalf = totalRow / 2
        totalPrice += frontHalf * seatPerRow * 10
        totalPrice += (totalRow - frontHalf) * seatPerRow * 8
    }
    return totalPrice
}

fun buyTicket(
    seats: MutableList<MutableList<String>>,
    totalRow: Int,
    seatPerRow: Int
): Pair<MutableList<MutableList<String>>, Int> {
    println()
    println("Enter a row number:")
    val selectedRow = readln().toInt()
    println("Enter a seat number in that row:")
    val selectedSeat = readln().toInt()

    println()
    if (selectedRow > totalRow || selectedSeat > seatPerRow) {
        println("Wrong input!")
        return buyTicket(seats, totalRow, seatPerRow)
    }
    if (seats[selectedRow][selectedSeat] == " B") {
        println("That ticket has already been purchased!")
        return buyTicket(seats, totalRow, seatPerRow)
    }


    val totalSeat = totalRow * seatPerRow
    val ticketPrice = when {
        totalSeat > 60 -> {
            val frontHalf = totalRow / 2
            if (selectedRow <= frontHalf) 10 else 8
        }

        else -> {
            10
        }
    }
    println("Ticket price: $$ticketPrice")
    seats[selectedRow][selectedSeat] = " B"
    return Pair(seats, ticketPrice)
}

fun makeSeatList(totalRow: Int, seatPerRow: Int): MutableList<MutableList<String>> {
    val seats = mutableListOf<MutableList<String>>()
    for (row in 0..totalRow) {
        val rowList = mutableListOf<String>()
        for (seat in 0..seatPerRow) {
            if (row == 0) {
                if (seat == 0) {
                    rowList.add(" ")
                } else {
                    rowList.add(" $seat")
                }
            } else {
                if (seat == 0) {
                    rowList.add("$row")
                } else {
                    rowList.add(" S")
                }
            }
        }
        seats.add(rowList)
    }
    return seats
}

fun printSeats(seats: MutableList<MutableList<String>>) {
    println()
    println("Cinema:")
    seats.forEach {
        it.forEach {seat ->
            print(seat)
        }
        println()
    }
}

fun statistics(seats: MutableList<MutableList<String>>, currentIncome: Int) {
    val totalRow = seats.last().first().trim().toInt()
    val seatPerRow = seats[0].last().trim().toInt()
    val totalSeats = totalRow * seatPerRow

    var purchasedTickets = 0

    var totalPossibleIncome = totalPossibleIncome(totalRow, seatPerRow)

    seats.forEach {
        purchasedTickets += it.count {
            it == " B"
        }
    }
    val percentage: Double = purchasedTickets/totalSeats.toDouble()*100

    println()
    println("Number of purchased tickets: $purchasedTickets")
    println("Percentage: ${"%.2f".format(percentage)}%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalPossibleIncome")
}


fun main() {


    println("Enter the number of rows:")
    val totalRow = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatPerRow = readln().toInt()

    var seats = makeSeatList(totalRow, seatPerRow)
    var currentIncome = 0

    while (true) {
        printInstruction()
        val selectedInstruction = readln().toInt()
        when(selectedInstruction) {
            1 -> printSeats(seats)
            2 -> {
                var result = buyTicket(seats, totalRow, seatPerRow)
                seats = result.first
                currentIncome += result.second
            }
            3 -> {
                statistics(seats, currentIncome)
            }
            0 -> break
        }
    }
}