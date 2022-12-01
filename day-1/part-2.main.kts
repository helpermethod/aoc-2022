#!/usr/bin/env kotlin

import java.io.File

File("input.txt")
    .readText()
    .trimEnd()
    .split("\n\n".toRegex())
    .map {
        it
            .split("\n".toRegex())
            .sumOf(String::toInt)
    }
    .sortedDescending()
    .take(3)
    .sum()
