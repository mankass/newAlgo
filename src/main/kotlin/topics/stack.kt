package org.example.topics

import java.util.Stack

/**
 * Check if string with '(', ')', '{', '}', '[', ']' has valid bracket pairing.
 * [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
 */
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

/**
 * For each day, find days until a warmer temperature appears.
 * [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)
 */
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

/**
 * Apply discount: for each item, subtract the first smaller or equal price to its right.
 * [1475. Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/)
 */
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

/** Monotonic stack template: for each element find the next greater element to the right. */
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

/**
 * Calculate exclusive execution time for n functions from a log of start/end events.
 * [636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/)
 */
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

/**
 * Evaluate an expression in Reverse Polish Notation (postfix) using stack.
 * [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)
 */
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

/**
 * Remove duplicate letters to get the smallest lexicographical result preserving order.
 * [316. Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/)
 */
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
