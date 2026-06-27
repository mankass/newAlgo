package org.example

fun longestCommonPrefix(strs: Array<String>): String {
    var prefix = strs.first()
    for (word in strs) {
        while (word.take(prefix.length) != prefix) {
            prefix = prefix.take(prefix.length - 1)
        }
    }
    return prefix
}

fun romanToInt(s: String): Int {
    var number = 0
    var prevValue = 0
    var index = s.length - 1

    while (index >= 0) {
        val currentValue = getValue(s[index])

        if (currentValue < prevValue) {
            number -= currentValue
        } else {
            number += currentValue
        }

        prevValue = currentValue
        index--
    }

    return number
}

fun getValue(c: Char): Int {
    return when (c) {
        'I' -> 1
        'V' -> 5
        'X' -> 10
        'L' -> 50
        'C' -> 100
        'D' -> 500
        'M' -> 1000
        else -> 0
    }
}

fun removeDuplicateLetters(s: String): String {
    val result = mutableListOf<Char>()
    for (c in s) {
        if (!result.contains(c)) {
            result.add(c)
        }
    }
    result.sort()
    val sb = StringBuilder()
    for (r in result) {
        sb.append(r)
    }
    return sb.toString()
}

fun removeSmiles(input: String): String {
    val result = StringBuilder()
    var i = 0
    val n = input.length

    while (i < n) {
        if (i + 2 < n && input[i] == ':' && input[i + 1] == '-') {
            val bracket = input[i + 2]

            if (bracket == ')' || bracket == '(') {
                i += 3

                while (i < n && input[i] == bracket) {
                    i++
                }
                continue
            }
        }

        result.append(input[i])
        i++
    }

    return result.toString()
}

fun convertToTitle(columnNumber: Int): String {
    var result = ""
    var column = columnNumber
    var resultList = mutableListOf<Int>()
    var tempInder = 0
    if (column <= 26) {
        return getLetterByNumber(column).toString()
    }
    while (column / 26 >= 1 && column != 26) {
        if (resultList.isEmpty()) {
            resultList.add(1)
            column -= 26
        }

        if (resultList[tempInder] == 26) {
            resultList[tempInder] = 1
            tempInder++
            resultList.add(0)
        }

        if (column <= 26) {
            resultList.add(column)
            column == 0
        }
        if (tempInder > 0 && column > 26) {
            resultList[tempInder - 1] = resultList[tempInder - 1] + 1
            column -= 26
        }
        if (tempInder == 0 && column > 26) {
            resultList[tempInder] = resultList[tempInder] + 1
        }
    }

    for (i in resultList.indices) {
        result += getLetterByNumber(resultList[i])
    }
    return result
}

fun getLetterByNumber(num: Int): Char {
    return ('A' + num - 1).toChar()
}
