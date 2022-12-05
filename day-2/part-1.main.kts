#!/usr/bin/env kotlin

import Part_1_main.Shape.PAPER
import Part_1_main.Shape.ROCK
import Part_1_main.Shape.SCISSOR
import java.io.File

enum class Shape(private val value: Int) {
    ROCK(1) {
        override fun winsAgainst() = SCISSOR
        override fun losesAgainst() = PAPER
    },
    PAPER(2) {
        override fun winsAgainst() = ROCK
        override fun losesAgainst() = SCISSOR
    },
    SCISSOR(3) {
        override fun winsAgainst() = PAPER
        override fun losesAgainst() = ROCK
    };

    private fun drawsAgainst(): Shape = this
    abstract fun winsAgainst(): Shape
    abstract fun losesAgainst(): Shape

    fun playAgainst(shape: Shape) =
        value + when (shape) {
            winsAgainst() -> 6
            losesAgainst() -> 3
            else -> 0
        }
}

data class Game(private val first: Shape, private val second: Shape) {
    fun play() = second.playAgainst(first)
}

fun createShape(token: String) =
    when (token) {
        "A", "X" -> ROCK
        "B", "Y" -> PAPER
        else -> SCISSOR
    }

fun createGame(first: String, second: String) = Game(createShape(first), createShape(second))

File("input.txt")
    .useLines { lines ->
        lines
            .map { it.split(" ") }
            .map { (first, second) -> createGame(first, second) }
            .map(Game::play)
            .sum()
    }
