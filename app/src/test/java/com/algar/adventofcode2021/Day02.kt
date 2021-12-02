package com.algar.adventofcode2021

import com.algar.adventofcode2021.Day02.Direction.DOWN
import com.algar.adventofcode2021.Day02.Direction.FORWARD
import com.algar.adventofcode2021.Day02.Direction.UP
import com.algar.adventofcode2021.Day02.Direction.valueOf
import java.io.BufferedReader
import java.io.InputStreamReader
import org.junit.Test

class Day02 {

    @Test
    fun `task 1 - submarines horizontal * depth position`() {
        var horizontal = 0
        var depth = 0
        getInput().forEach {
            when (it.first) {
                FORWARD -> horizontal += it.second
                DOWN -> depth += it.second
                UP -> depth -= it.second
            }
        }
        println("Task 1 - Horizontal($horizontal) * Depth($depth) == ${horizontal * depth}")
    }

    @Test
    fun `task 2 - submarine horizontal * depth position given aim`() {
        var horizontal = 0
        var depth = 0
        var aim = 0
        getInput().forEach {
            when (it.first) {
                FORWARD -> {
                    horizontal += it.second
                    depth += aim * it.second
                }
                DOWN -> aim += it.second
                UP -> aim -= it.second
            }
        }
        println("Task 2 - Horizontal($horizontal) * Depth($depth) == ${horizontal * depth}")
    }

    private enum class Direction {
        FORWARD, DOWN, UP
    }

    private fun getInput(): List<Pair<Direction, Int>> {
        val output = arrayListOf<Pair<Direction, Int>>()
        val inputStream = javaClass.getResourceAsStream("day02.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            val directionAndValue = line?.split(" ") ?: continue
            output.add(
                valueOf(
                    directionAndValue.first().uppercase()
                ) to directionAndValue[1].toInt()
            )
        }
        return output
    }
}