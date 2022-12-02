#!/usr/bin/env kotlin

import java.io.File

sealed class Shape(private val value: Int) {
    fun draws(): Shape = this
    abstract fun wins(): Shape
    abstract fun loses(): Shape
    fun interactWith(shape: Shape) =
        value + when (shape) {
            wins() -> 6
            draws() -> 3
            else -> 0
        }
}

object Rock : Shape(1) {
    override fun wins() = Scissor
    override fun loses() = Paper
}

object Paper : Shape(2) {
    override fun wins() = Rock
    override fun loses() = Scissor
}

object Scissor : Shape(3) {
    override fun wins() = Paper
    override fun loses() = Rock
}

data class Game(private val first: Shape, private val outcome: (Shape) -> Shape) {
    fun play() = outcome(first).interactWith(first)
}

fun createShape(token: String) =
    when(token) {
        "A" -> Rock
        "B" -> Paper
        else -> Scissor
    }

fun createOutcome(token: String) =
    when (token) {
        "X" -> Shape::loses
        "Y" -> Shape::draws
        else -> Shape::wins
    }

fun createGame(first: String, second: String) =
    Game(createShape(first), createOutcome(second))

File("input.txt")
    .useLines { lines ->
        lines
            .map { it.split(" ") }
            .map { (first, second) -> createGame(first, second) }
            .map(Game::play)
            .sum()
    }
