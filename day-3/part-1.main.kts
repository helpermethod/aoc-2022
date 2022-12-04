#!/usr/bin/env kotlin

import java.io.File

val priorities =
    (('A'..'Z').zip(27..52) + ('a'..'z').zip(1..27)).toMap()

fun compartmentalize(line: String) =
    line.toList().chunked(line.length / 2)

File("input.txt")
    .useLines { lines ->
        lines
            .map(::compartmentalize)
            .map { (first, second) -> first.intersect(second.toSet()).first() }
            .sumOf { priorities[it]!! }
    }
