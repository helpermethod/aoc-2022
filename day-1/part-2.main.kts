#!/usr/bin/env kotlin

import java.io.File

File("input.txt")
    .readText()
    .trimEnd()
    .split("\n\n")
    .map { it.lines().sumOf(String::toInt) }
    .sortedDescending()
    .take(3)
    .sum()
