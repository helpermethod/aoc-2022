#!/usr/bin/env kotlin

import java.io.File

val priorities =
    (('A'..'Z').zip(27..52) + ('a'..'z').zip(1..27)).toMap()

fun backpacks(group: List<String>) =
    group.map { it.toSet() }

File("input.txt")
    .useLines { lines ->
        lines
            .chunked(3)
            .map(::backpacks)
            .map { backpacks ->
                backpacks.reduce { left, right -> left.intersect(right) }.first()
            }
            .sumOf { priorities[it]!! }
    }

