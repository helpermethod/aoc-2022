#!/usr/bin/env kotlin

import java.io.File

fun String.toRangeSet() =
    split("-").let { (start, end) -> (start.toInt()..end.toInt()).toSet() }

File("input.txt")
    .useLines { lines ->
        lines
            .map { it.split(",") }
            .map { (first, second) -> first.toRangeSet() to second.toIntSet() }
            .count { (first, second) ->
                when {
                    first.containsAll(second) -> true
                    second.containsAll(first) -> true
                    else -> false
                }
            }
    }
