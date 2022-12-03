#!/usr/bin/env kotlin

import Part_2_main.Shape.PAPER
import Part_2_main.Shape.ROCK
import Part_2_main.Shape.SCISSOR
import java.io.File

enum class Shape(private val value: Int) {
    ROCK(1) {
        override fun wins() = SCISSOR
        override fun loses() = PAPER
    },
    PAPER(2) {
        override fun wins() = ROCK
        override fun loses() = SCISSOR
    },
    SCISSOR(3) {
        override fun wins() = PAPER
        override fun loses() = ROCK
    };

    fun draws(): Shape = this
    abstract fun wins(): Shape
    abstract fun loses(): Shape
    fun playAgainst(shape: Shape) =
        value + when (shape) {
            wins() -> 6
            draws() -> 3
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
        "X" -> Shape::wins
        "Y" -> Shape::draws
        else -> Shape::loses
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
