package parking

var parkingLot = emptyArray<Array<String>>()

fun main() {
    while (true) {
        val input = readln().split(" ")
        if (isNotCreated(input[0])) continue
        when (input[0]) {
            "park" -> park(input[1], input[2])
            "leave" -> leave(input[1].toInt())
            "status" -> status()
            "create" -> create(input[1].toInt())
            "reg_by_color" -> regByColor(input[1])
            "spot_by_color" -> spotByColor(input[1])
            "spot_by_reg" -> spotByReg(input[1])
            "exit" -> break
        }
    }
}

fun park(reg: String, color: String) {
    for (i in parkingLot.indices) {
        if (parkingLot[i].isEmpty()) {
            parkingLot[i] = arrayOf(reg, color)
            println("$color car parked in spot ${i + 1}.")
            return
        }
    }
    println("Sorry, the parking lot is full.")
}

fun leave(spot: Int) {
    if (parkingLot[spot - 1].isEmpty()) {
        println("There is no car in spot $spot.")
    } else {
        parkingLot[spot - 1] = emptyArray()
        println("Spot $spot is free.")
    }
}

fun status() {
    var empty = true
    for (i in parkingLot.indices) {
        if (parkingLot[i].isNotEmpty()) {
            empty = false
            println("${i + 1} ${parkingLot[i][0]} ${parkingLot[i][1]}")
        }
    }
    if (empty) println("Parking lot is empty.")
}

fun isNotCreated(command: String): Boolean {
    if (parkingLot.isNotEmpty() || command == "create" || command == "exit") {
        return false
    }
    println("Sorry, a parking lot has not been created.")
    return true
}

fun create(size: Int) {
    parkingLot = Array(size) { emptyArray() }
    println("Created a parking lot with $size spots.")
}

fun regByColor(color: String) {
    var hasCars = false
    for (spot in parkingLot) {
        if (spot.isEmpty()) continue
        if (spot[1].lowercase() == color.lowercase()) {
            if (hasCars) {
                print(", ")
            }
            hasCars = true
            print(spot[0])
        }
    }
    if (!hasCars) {
        print("No cars with color $color were found.")
    }
    println()
}

fun spotByColor(color: String) {
    var hasCars = false
    for (i in parkingLot.indices) {
        if (parkingLot[i].isEmpty()) continue
        if (parkingLot[i][1].lowercase() == color.lowercase()) {
            if (hasCars) {
                print(", ")
            }
            hasCars = true
            print(i + 1)
        }
    }
    if (!hasCars) {
        print("No cars with color $color were found.")
    }
    println()
}

fun spotByReg(reg: String) {
    var hasCars = false
    for (i in parkingLot.indices) {
        if (parkingLot[i].isEmpty()) continue
        if (parkingLot[i][0] == reg) {
            if (hasCars) {
                print(", ")
            }
            hasCars = true
            print(i + 1)
        }
    }
    if (!hasCars) {
        print("No cars with registration number $reg were found.")
    }
    println()
}