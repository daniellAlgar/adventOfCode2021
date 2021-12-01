package com.algar.adventofcode2021

import java.io.BufferedReader
import java.io.InputStreamReader
import org.junit.Test

class Day01 {

    @Test
    fun `task 1 - how many measurements are larger than the previous`() {
        val input = getInput()

        val diff = input.drop(1).mapIndexed { index, depth ->
            depth - input[index]
        }
        val result = diff.count { it > 0 }
        println("Task 1 - Number of positive derivatives: $result")
    }

    private fun getInput(): List<Int> {
        val output = arrayListOf<Int>()
        val inputStream = javaClass.getResourceAsStream("day01_input1.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            line?.toInt()?.let { output.add(it) }
        }
        return output
    }
}
