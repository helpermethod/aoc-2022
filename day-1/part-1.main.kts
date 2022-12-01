#!/usr/bin/env kotlin

import java.io.File

File("input.txt")
    .readText()
    .trimEnd()
    .split("\n\n".toRegex())
    .maxOfOrNull {
        it
            .split("\n".toRegex())
            .sumOf(String::toInt)
    }
