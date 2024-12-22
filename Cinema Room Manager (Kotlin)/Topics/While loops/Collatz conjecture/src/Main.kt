fun main() {
    // put your code here
    var n: Int = readlnOrNull()?.toInt() ?: 0
    print(" $n")
    while (n != 1) {
        if (n % 2 == 0) {
            n /= 2
        } else {
            n = n*3 + 1
        }
        print(" $n")

    }
}