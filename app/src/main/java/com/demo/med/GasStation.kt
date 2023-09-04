package com.demo.med

object GasStation {
    fun findStartingStation(strArr: Array<String>): String {
        val n = strArr[0].toInt()
        var totalGas = 0
        var currentGas = 0
        var startingStation = 0

        for (i in 1..n) {
            val parts = strArr[i].split(":")
            val gas = parts[0].toInt()
            val cost = parts[1].toInt()

            totalGas += gas - cost
            currentGas += gas - cost

            // If the currentGas is negative, we cannot start from this station
            if (currentGas < 0) {
                currentGas = 0
                startingStation = i
            }
        }

        return if (totalGas >= 0) (startingStation + 1).toString() else "impossible"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val input1 = arrayOf("4", "1:1", "2:2", "1:2", "0:1")
        val output1 = findStartingStation(input1)
        println("Input: ${input1.joinToString(", ")}")
        println("Output: $output1")

        val input2 = arrayOf("4", "0:1", "2:2", "1:2", "3:1")
        val output2 = findStartingStation(input2)
        println("Input: ${input2.joinToString(", ")}")
        println("Output: $output2")
    }
}
