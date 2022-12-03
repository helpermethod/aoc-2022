#!/usr/bin/env kotlin

import java.io.File

val translate =
    (('A'..'Z').zip(27..52) + ('a'..'z').zip(1..27)).toMap()

File("input.txt")
    .useLines { lines ->
        lines
            .chunked(3)
            .map { chunk ->
                val elves = chunk.map { it.toSet() }

                elves.reduce { left, right ->
                    left.intersect(right)
                }.first()
            }
            .sumOf { translate[it]!! }
    }

