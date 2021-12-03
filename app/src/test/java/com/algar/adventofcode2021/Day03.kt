package com.algar.adventofcode2021

import java.io.BufferedReader
import java.io.InputStreamReader
import org.junit.Test

class Day03 {

    @Test
    fun `task 1 - power consumption`() {
        val input = getInput()
        val gammaRate = arrayListOf<Boolean>()
        (0 until input.first().count()).forEachIndexed { index, _ ->
            gammaRate.add(input.count { it[index] } < input.size / 2)
        }
        val epsilonRate = gammaRate.map { !it }

        println("Gamma rate: $gammaRate")
        println("Epsilon rate: $epsilonRate")
    }

    @Test
    fun `task 2 - life support rating`() {
        val input = getInput()
        var oxygenRating = input
        var co2Rating = input

        var currentBit = 0
        while (oxygenRating.size > 1) {
            val keepFlag =
                oxygenRating.count { it[currentBit] } >= oxygenRating.count { !it[currentBit] }
            oxygenRating = oxygenRating.filter { it[currentBit] == keepFlag }
            currentBit++
        }

        currentBit = 0
        while (co2Rating.size > 1) {
            val keepFlag = co2Rating.count { it[currentBit] } < co2Rating.count { !it[currentBit] }
            co2Rating = co2Rating.filter { it[currentBit] == keepFlag }
            currentBit++
        }

        println("Oxygen rating: $oxygenRating, CO2 rating: $co2Rating")
    }

    private fun getInput(): List<List<Boolean>> {
        val output = arrayListOf<List<Boolean>>()
        val inputStream = javaClass.getResourceAsStream("day03.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            line?.let { output.add(it.toCharArray().map { char -> char == '1' }) }
        }
        return output
    }
}