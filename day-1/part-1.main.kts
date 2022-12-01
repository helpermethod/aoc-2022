#!/usr/bin/env kotlin

import java.io.File

File("input.txt")
    .readText()
    .trimEnd()
    .split("\n\n")
    .maxOf { it.lines().sumOf(String::toInt) }
