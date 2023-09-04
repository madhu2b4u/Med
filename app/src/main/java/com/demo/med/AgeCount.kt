package com.demo.med

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

object AgeCount {
    @JvmStatic
    fun main(args: Array<String>) {
        System.setProperty("http.agent", "Chrome")
        try {
            val url = URL("https://coderbyte.com/api/challenges/json/age-counting")
            val connection = url.openConnection()
            connection.setRequestProperty("Accept-Charset", "UTF-8")
            connection.setRequestProperty("User-Agent", "Mozilla/5.0")

            // Optional: Set other request properties if needed
            connection.connect()
            val `in` = BufferedReader(InputStreamReader(connection.getInputStream()))
            var inputLine: String?
            val response = StringBuilder()
            while (`in`.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            `in`.close()
            val json = response.toString()
            val count = countAges(json, 50)
            println(count)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    //age=5"}
    private fun countAges(json: String, threshold: Int): Int {
        var count = 0
        val items = json.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (item in items) {
            if (item.startsWith("age=")) {
                val age = item.substring(4).toInt()
                if (age >= threshold) {
                    count++
                }
            }
        }
        return count
    }
}