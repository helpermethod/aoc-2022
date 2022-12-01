#!/usr/bin/env kotlin

import java.io.File

File("input.txt")
    .readText()
    .trimEnd()
    .split("\n\n".toRegex())
    .maxOfOrNull { it.lines().sumOf(String::toInt) }
