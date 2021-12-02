package com.algar.adventofcode2021

import com.algar.adventofcode2021.Day02.Direction.*
import java.io.BufferedReader
import java.io.InputStreamReader
import org.junit.Test

class Day02 {

    @Test
    fun `task 1 - submarines horizontal * depth position`() {
        var forward = 0
        var depth = 0
        getInput().forEach {
            when (it.first) {
                FORWARD -> forward += it.second
                DOWN -> depth += it.second
                UP -> depth -= it.second
            }
        }
        println("Task 1 - Forward($forward) * Depth($depth) == ${forward * depth}")
    }

    private enum class Direction {
        FORWARD, DOWN, UP
    }

    private fun getInput(): List<Pair<Direction, Int>> {
        val output = arrayListOf<Pair<Direction, Int>>()
        val inputStream = javaClass.getResourceAsStream("day02_input1.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            val directionAndValue = line?.split(" ") ?: continue
            output.add(valueOf(directionAndValue.first().uppercase()) to directionAndValue[1].toInt())
        }
        return output
    }
}