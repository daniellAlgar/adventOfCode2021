package com.algar.adventofcode2021

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max
import kotlin.math.pow
import kotlin.math.sqrt
import org.junit.Test

typealias X = Int
typealias Y = Int

class Day05 {

    @Test
    fun `task 1 - at least 2 overlapping lines`() {
        val input = getInput().filter { it.p1.first == it.p2.first || it.p1.second == it.p2.second }
        val hydroThermalMap = plotLines(input)
        println(
            "Task 1 - Number of at least 2 overlapping lines: ${
                hydroThermalMap.flatten().count { it > 1 }
            }"
        )
    }

    private fun plotLines(input: List<Line>): MutableList<MutableList<Int>> {
        val hydroThermalMap = initHydroThermalMap(input)
        input.forEach { line ->
            hydroThermalMap.forEachIndexed { yIndex, list ->
                list.forEachIndexed { xIndex, _ ->
                    val c = Pair(xIndex, yIndex)
                    if (distance(p1 = line.p1, p2 = c) + distance(p1 = line.p2, p2 = c)
                        == distance(p1 = line.p1, p2 = line.p2)
                    ) {
                        hydroThermalMap[yIndex][xIndex]++
                    }
                }
            }
        }
        return hydroThermalMap
    }

    private fun distance(p1: Pair<X, Y>, p2: Pair<X, Y>) =
        sqrt((p1.first - p2.first).toDouble().pow(2) + (p1.second - p2.second).toDouble().pow(2))

    private fun initHydroThermalMap(input: List<Line>): MutableList<MutableList<Int>> {
        val maxX = input.map { max(it.p1.first, it.p2.first) }.maxOf { it }
        val maxY = input.map { max(it.p1.second, it.p2.second) }.maxOf { it }
        return (0 until maxY + 1).map { mutableListOf<Int>().also { x -> repeat(maxX + 1) { x.add(0) } } } as MutableList<MutableList<Int>>
    }

    data class Line(val p1: Pair<X, Y>, val p2: Pair<X, Y>)

    private fun getInput(): List<Line> {
        val output = arrayListOf<Line>()
        val inputStream = javaClass.getResourceAsStream("day05.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            val regex = Regex("(\\d+),(\\d+)")
            line?.let {
                val p1 = regex.find(it)!!
                val p2 = p1.next()!!
                output.add(
                    Line(
                        p1 = p1.groups[1]!!.value.toInt() to p1.groups[2]!!.value.toInt(),
                        p2 = p2.groups[1]!!.value.toInt() to p2.groups[2]!!.value.toInt()
                    )
                )
            }
        }
        return output
    }
}