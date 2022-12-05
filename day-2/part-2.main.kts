#!/usr/bin/env kotlin

import Part_2_main.Shape.PAPER
import Part_2_main.Shape.ROCK
import Part_2_main.Shape.SCISSOR
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

    fun drawsAgainst(): Shape = this
    abstract fun winsAgainst(): Shape
    abstract fun losesAgainst(): Shape

    fun playAgainst(shape: Shape) =
        value + when (shape) {
            winsAgainst() -> 6
            drawsAgainst() -> 3
            else -> 0
        }
}

data class Game(private val first: Shape, private val selectOutcome: Shape.() -> Shape) {
    fun play() = first.selectOutcome().playAgainst(first)
}

fun createShape(token: String) =
    when (token) {
        "A" -> ROCK
        "B" -> PAPER
        else -> SCISSOR
    }

fun createOutcomeSelector(token: String) =
    when (token) {
        "X" -> Shape::winsAgainst
        "Y" -> Shape::drawsAgainst
        else -> Shape::losesAgainst
    }

fun createGame(first: String, second: String) =
    Game(createShape(first), createOutcomeSelector(second))

File("input.txt")
    .useLines { lines ->
        lines
            .map { it.split(" ") }
            .map { (first, second) -> createGame(first, second) }
            .map(Game::play)
            .sum()
    }
