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