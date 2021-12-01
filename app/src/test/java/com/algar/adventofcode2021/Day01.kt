package com.algar.adventofcode2021

import java.io.BufferedReader
import java.io.InputStreamReader
import org.junit.Test

class Day01 {

    @Test
    fun `task 1 - how many measurements are larger than the previous`() {
        val result = diff(getInput()).count { it > 0 }
        println("Task 1 - Number of positive derivatives: $result.")
    }

    @Test
    fun `task 2 - like task 1 but with a 3 measurement sliding window`() {
        val result = diff(getSlidingWindow(getInput())).count { it > 0 }
        println("Task 2 - Number of positive derivatives: $result.")
    }

    private fun diff(input: List<Int>): List<Int> = input.drop(1).mapIndexed { index, depth ->
        depth - input[index]
    }

    private fun getSlidingWindow(input: List<Int>): List<Int> =
        (0..input.size - 3).mapIndexed { index, _ ->
            input[index] + input[index + 1] + input[index + 2]
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
