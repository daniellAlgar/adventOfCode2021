package com.algar.adventofcode2021

import java.io.BufferedReader
import java.io.InputStreamReader
import org.junit.Test

typealias Boards = List<List<Int>>

class Day04 {

    @Test
    fun `task 1 - winning bingo board`() {
        val (numbers, boards) = getInput()
        val boardMatches = initBoardMatches(given = boards)
        numbers.forEach { drawnNumber ->
            boards.forEachIndexed { boardIndex, board ->
                board.forEachIndexed { cellIndex, cellValue ->
                    if (cellValue == drawnNumber) boardMatches[boardIndex][cellIndex] = 1
                    else return@forEachIndexed
                    if (bingo(boardMatches[boardIndex])) {
                        val score =
                            drawnNumber * sumOfUnmarkedNumbers(board, boardMatches[boardIndex])
                        println("Score: $score")
                        return
                    }
                }
            }
        }
    }

    private fun sumOfUnmarkedNumbers(board: List<Int>, matches: List<Int>): Int {
        var sum = 0
        board.forEachIndexed { index, i -> if (matches[index] == 0) sum += i }
        return sum
    }

    private fun bingo(matches: List<Int>) = bingoHorizontal(matches) || bingoVertical(matches)

    private fun bingoHorizontal(matches: List<Int>): Boolean {
        (0 until 24 step 5).forEach {
            if (matches.subList(it, it + 5).sum() == 5) return true
        }
        return false
    }

    private fun bingoVertical(matches: List<Int>): Boolean {
        repeat(5) { if (matches.slice(it until 24 step 5).sum() == 5) return true }
        return false
    }

    private fun initBoardMatches(given: Boards): MutableList<MutableList<Int>> =
        (given.indices).map {
            mutableListOf<Int>().also { board -> repeat(given.first().size) { board.add(0) } }
        } as MutableList<MutableList<Int>>

    private data class BingoSystem(
        val numbers: List<Int>,
        val boards: Boards
    )

    private fun getInput(): BingoSystem {
        val inputStream = javaClass.getResourceAsStream("day04.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        val allBoards = arrayListOf<Int>()
        val numbers = reader.readLine().split(",").map { it.toInt() }

        reader.readLine()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            if (line == null || line!!.isEmpty()) continue
            allBoards.addAll(line!!.trimStart().split(Regex(" +")).map { value -> value.toInt() })
        }
        val boards = (0 until allBoards.size - 1 step 25).map { allBoards.subList(it, it + 25) }
        return BingoSystem(numbers, boards)
    }
}