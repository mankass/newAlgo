package org.example

import java.util.Stack

fun isValid(s: String): Boolean {
    val stack = Stack<Char>()
    for (char in s.toCharArray()) {
        when (char) {
            '(' -> stack.push(char)
            '[' -> stack.push(char)
            '{' -> stack.push(char)
            ')' -> if (stack.isNotEmpty() && stack.peek() == '(') stack.pop() else return false
            '}' -> if (stack.isNotEmpty() && stack.peek() == '{') stack.pop() else return false
            ']' -> if (stack.isNotEmpty() && stack.peek() == '[') stack.pop() else return false
        }
    }
    return stack.isEmpty()
}

fun dailyTemperatures(temperatures: IntArray): IntArray {
    val stack = ArrayDeque<Int>()
    val result = IntArray(temperatures.size)
    for (i in temperatures.indices) {
        while (stack.isNotEmpty() && temperatures[i] > temperatures[stack.last()]) {
            val j = stack.last()
            stack.removeLast()
            result[j] = i - j
        }
        stack.add(i)
    }
    return result
}

fun finalPrices(prices: IntArray): IntArray {
    val stack = ArrayDeque<Int>()
    val res = prices
    for (i in prices.indices) {
        while (stack.isNotEmpty() && prices[i] <= prices[stack.last()]) {
            val j = stack.last()
            stack.removeLast()
            res[j] = prices[j] - prices[i]
        }
        stack.add(i)
    }
    return res
}

fun monoStack(arr: IntArray): IntArray {
    val stack = ArrayDeque<Int>()
    val res = IntArray(arr.size) { -1 }
    for (ind in arr.indices) {
        while (stack.isNotEmpty() && arr[ind] > arr[stack.last()]) {
            val j = stack.last()
            stack.removeLast()
            res[j] = arr[ind]
        }
        stack.add(ind)
    }
    return res
}

fun exclusiveTime(n: Int, logs: List<String>): IntArray {
    val stack = ArrayDeque<Int>()
    val result = IntArray(n)
    for (log in logs) {
        if (stack.isEmpty()) {
            stack.add(log.last().toInt())
        }
        if (log.contains("start")) {
            stack.add(log.toInt() - stack.last())
        }
        if (log.contains("end")) {
            result[log.first().toInt()] = log.last().toInt() - stack.last()
            stack.removeLast()
        }
    }
    return result
}

fun evalRPN(tokens: Array<String>): Int {
    val stack = ArrayDeque<Int>()
    for (token in tokens) {
        when (token) {
            "+" -> {
                val b = stack.removeLast()
                val a = stack.removeLast()
                stack.addLast(a + b)
            }

            "-" -> {
                val b = stack.removeLast()
                val a = stack.removeLast()
                stack.addLast(a - b)
            }

            "*" -> {
                val b = stack.removeLast()
                val a = stack.removeLast()
                stack.addLast(a * b)
            }

            "/" -> {
                val b = stack.removeLast()
                val a = stack.removeLast()
                stack.addLast(a / b)
            }

            else -> stack.addLast(token.toInt())
        }
    }
    return stack.last()
}

fun removeDuplicateLetters22(s: String): String {
    val lastIndex = IntArray(26) { -1 }
    for (i in s.indices) {
        lastIndex[s[i] - 'a'] = i
    }
    val stack = mutableListOf<Char>()
    val seen = BooleanArray(26)
    for (i in s.indices) {
        val c = s[i]
        if (seen[c - 'a']) continue
        while (stack.isNotEmpty() && c < stack.last() && lastIndex[stack.last() - 'a'] > i) {
            seen[stack.removeAt(stack.size - 1) - 'a'] = false
        }
        stack.add(c)
        seen[c - 'a'] = true
    }
    return stack.joinToString("")
}
