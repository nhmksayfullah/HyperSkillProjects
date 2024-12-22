// import required libraries
import java.util.*

// main function
fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // take input
    val n = scanner.nextInt()

    //  initialize the first two integers of the Fibonacci series to 0 and 1 respectively
    var t1 = 0
    var t2 = 1
    var i = 0
    // Write your "while" loop code here to complete the series
    while (i < n) {
        print(if (i == n-1) "$t1" else "$t1, ")
        val temp = t1 + t2
        t1 = t2
        t2 = temp
        i++
    }
    // print the series
}