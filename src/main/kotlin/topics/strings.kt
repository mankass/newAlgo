package org.example.topics

/**
 * Найти самый длинный общий префикс среди массива строк.
 * [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)
 */
fun longestCommonPrefix(strs: Array<String>): String {
    var prefix = strs.first()
    for (word in strs) {
        while (word.take(prefix.length) != prefix) {
            prefix = prefix.take(prefix.length - 1)
        }
    }
    return prefix
}

/**
 * Преобразовать римскую строку в целое число.
 * [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/)
 */
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

/** Преобразовать римский символ в число. Хелпер для romanToInt. */
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

/** Собрать уникальные символы строки и вернуть отсортированными по алфавиту. */
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

/** Удалить из строки все смайлики ":-)" и ":-(" вместе с идущими подряд такими же скобками. */
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

/**
 * Преобразовать номер колонки в заголовок Excel (1 -> A, 28 -> AB).
 * [168. Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title/)
 */
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

/** Преобразовать число 1-26 в соответствующую заглавную букву. Хелпер для convertToTitle. */
fun getLetterByNumber(num: Int): Char {
    return ('A' + num - 1).toChar()
}

/**
 * Проверить, изоморфны ли строки (символы s можно заменить и получить t).
 * [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/)
 */
fun isIsomorphic(s: String, t: String): Boolean {
    val mapTransform = HashMap<Char, Char>()
    val result = StringBuilder()
    val setUsing = HashSet<Char>()
    for(i in 0 until s.length){
        if (setUsing.contains(s[i])){
            result.append(mapTransform[s]) as Char
        }
    }
}

/**
 * Проверить, является ли строка палиндромом (учитывая только буквы и цифры).
 * [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
 */
fun isPalindrome(s: String): Boolean {
    var start = 0
    var end = s.length - 1

    while (start < end) {
        if (!s[start].isLetterOrDigit()) {
            start++
            continue
        }
        if (!s[end].isLetterOrDigit()) {
            end--
            continue
        }
        if (s[start].lowercase() != s[end].lowercase()) {
            return false
        }

        start++
        end--
    }
    return true
}

/**
 * Посчитать, сколько строк из patterns встречаются как подстроки в word.
 * [1967. Number of Strings That Appear as Substrings in Word](https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/)
 */
fun numOfStrings(patterns: Array<String>, word: String): Int {
    var count = 0
    for (words in patterns) {
        if (word.contains(words)) {
            count++
        }
    }
    return count
}

/**
 * Вернуть длину последнего слова в строке.
 * [58. Length of Last Word](https://leetcode.com/problems/length-of-last-word/)
 */
fun lengthOfLastWord(s: String): Int {
    var count = 0
    for (i in s.length - 1 downTo 0) {
        if (s[i] == ' ' && count > 0) {
            return count
        }
        if (s[i].isLetter()) {
            count++
        }
    }
    return count
}

/**
 * Найти индекс первого вхождения needle в haystack (или -1).
 * [28. Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/)
 */
fun strStr(haystack: String, needle: String): Int {
    var needleChar = needle.first()
    val needleSize = needle.length
    for (i in 0 until haystack.length - needle.length) {
        if (haystack[i] == needleChar) {
            if (haystack.length >= i + needle.length) {
                if (haystack.substring(i, i + needleSize) == needle) {
                    return i
                }
            }
        }
    }

    return -1
}
