package qualificationRound

import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    val input = sc.nextLine().split(" ")
    val humanNumber = input[0].toInt()
    val humanLimit = input[1].toInt()
    val timeLimit = input[2].toInt()
    val timeList = ArrayList<Int>()

    var timeListWithTimeLimit = ArrayList<Int>()
    val limitCounter = 0

    val line = sc.nextLine().split(" ")
    for (i in 0 until humanNumber) {
        timeList.add(line[i].toInt())
    }

    println(timeList.toList())
    timeList.bubbleSort()
    println(timeList.toList())

}

fun ArrayList<Int>.bubbleSort() {
    var sorted = false
    while (!sorted) {
        sorted = true
        for (i in 1 until this.size) {
            val previous = this[i - 1]
            val current = this[i]
            if (previous > current) {
                this.swap(i - 1, i)
                sorted = false
            }
        }
    }
}

fun ArrayList<Int>.swap(index1: Int, index2: Int) {
    val buffer = this[index1]
    this[index1] = this[index2]
    this[index2] = buffer
}