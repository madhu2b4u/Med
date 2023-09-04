package com.demo.med

object StepWalking {

    fun countWays(num: Int): Int {
        if (num <= 0) {
            return 0
        }

        if (num <= 2) {
            return num
        }

        val ways = IntArray(num + 1)
        ways[1] = 1
        ways[2] = 2

        for (i in 3..num) {
            ways[i] = ways[i - 1] + ways[i - 2]
        }

        return ways[num]
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val num = 3 // Change this value to test different inputs
        val result = countWays(num)
        println("Number of ways to climb $num steps: $result")
    }
}