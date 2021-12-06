package com.algar.adventofcode2021

import java.util.Collections
import org.junit.Test

class Day06 {
    private val initialState = listOf(3,1,5,4,4,4,5,3,4,4,1,4,2,3,1,3,3,2,3,2,5,1,1,4,4,3,2,4,2,4,1,5,3,3,2,2,2,5,5,1,3,4,5,1,5,5,1,1,1,4,3,2,3,3,3,4,4,4,5,5,1,3,3,5,4,5,5,5,1,1,2,4,3,4,5,4,5,2,2,3,5,2,1,2,4,3,5,1,3,1,4,4,1,3,2,3,2,4,5,2,4,1,4,3,1,3,1,5,1,3,5,4,3,1,5,3,3,5,4,2,3,4,1,2,1,1,4,4,4,3,1,1,1,1,1,4,2,5,1,1,2,1,5,3,4,1,5,4,1,3,3,1,4,4,5,3,1,1,3,3,3,1,1,5,4,2,5,1,1,5,5,1,4,2,2,5,3,1,1,3,3,5,3,3,2,4,3,2,5,2,5,4,5,4,3,2,4,3,5,1,2,2,4,3,1,5,5,1,3,1,3,2,2,4,5,4,2,3,2,3,4,1,3,4,2,5,4,4,2,2,1,4,1,5,1,5,4,3,3,3,3,3,5,2,1,5,5,3,5,2,1,1,4,2,2,5,1,4,3,3,4,4,2,3,2,1,3,1,5,2,1,5,1,3,1,4,2,4,5,1,4,5,5,3,5,1,5,4,1,3,4,1,1,4,5,5,2,1,3,3)

    @Test
    fun `task 1 & 2`() {
        val testData = listOf(3, 4, 3, 1, 2)
        assert(simulate(days = 18, initialState = testData) == 26L)
        assert(simulate(days = 80, initialState = testData) == 5934L)

        println("Nr of fish after 80 days: ${simulate(days = 80, initialState = initialState)}")
        println("Nr of fish after 256 days: ${simulate(days = 256, initialState = initialState)}")
    }

    private fun simulate(days: Int, initialState: List<Int>): Long {
        val pool = (0..8).map { 0L }.toMutableList()
        initialState.forEach { pool[it]++ }
        for (day in 1..days) {
            val pool0 = pool[0]
            Collections.rotate(pool, -1)
            pool[6] += pool0
            pool[8] = pool0
        }
        return pool.sum()
    }
}