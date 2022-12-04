#!/usr/bin/env kotlin

import java.io.File

fun String.toIntSet() =
    split("-").let { (start, end) -> (start.toInt()..end.toInt()).toSet() }

File("input.txt")
    .useLines { lines ->
        lines
            .map { it.split(",") }
            .map { (first, second) ->
                first.toIntSet() to second.toIntSet()
            }
            .count { (first, second) ->
                first.intersect(second).isNotEmpty()
            }
    }
