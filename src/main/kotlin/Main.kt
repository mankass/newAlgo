package org.example

import java.io.PrintStream
import java.util.Stack


fun main() {
    System.setOut(PrintStream(System.`out`, true, "UTF-8"))

    val res = dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73))
    println(res.toList())
}

fun removeDuplicates(nums: IntArray): Int {

    var prevValue = nums.first()
    var countUnique = 1
    for (i in 1 until nums.size) {
        if (nums[i]>prevValue){
            nums[countUnique] = nums[i]
            countUnique += 1
            prevValue = nums[i]
        }

    }
    return countUnique
}

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

fun longestCommonPrefix(strs: Array<String>): String {

    var prexix = strs.first()
    for (word in strs) {
        while (word.take(prexix.length) != prexix) {
            prexix = prexix.take(prexix.length - 1)
        }
    }
    return prexix
}


fun romanToInt(s: String): Int {
    var number = 0       // Здесь мы будем накапливать итоговый результат
    var prevValue = 0    // Здесь храним значение символа, который обработали на предыдущем шаге (справа)
    var index = s.length - 1 // Начинаем с самого последнего символа строки

    // Цикл идет справа налево, пока не дойдет до самого первого символа (индекс 0)
    while (index >= 0) {
        // Получаем числовое значение текущего символа с помощью вспомогательной функции
        val currentValue = getValue(s[index])

        // Сравниваем текущее значение с тем, что было справа (prevValue)
        if (currentValue < prevValue) {
            // Если текущее меньше предыдущего (например, I перед V, то есть 1 перед 5),
            // мы его вычитаем из общей суммы
            number -= currentValue
        } else {
            // Во всех остальных случаях (если текущее больше или равно предыдущему) — прибавляем
            number += currentValue
        }

        // Теперь текущее значение становится "предыдущим" для следующего шага цикла
        prevValue = currentValue

        // Переходим к следующему символу влево
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

fun countStudents(students: IntArray, sandwiches: IntArray): Int {
    val queue = students.toCollection(ArrayDeque())
    var countTry = 0
    var sanIndex = 0
    while (countTry < queue.size) {
        if (queue.first() == sandwiches[sanIndex]) {
            queue.removeFirst()
            sanIndex += 1
            countTry = 0
        } else {
            queue.addLast(queue.first())
            queue.removeFirst()
            countTry += 1
        }
    }
    return queue.size
}


fun removeDuplicateLetters(s: String): String {
    val result = mutableListOf<Char>()
    for (s in s) {
        if (result.contains(s)) {

        } else {

            result.add(s)
        }
    }
    result.sort()
    var sv = StringBuilder()
    for (r in result) {
        sv.append(r)
    }
    return sv.toString()
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

fun removeSmiles(input: String): String {
    val result = StringBuilder() // 1
    var i = 0 // 2
    val n = input.length

    while (i < n) { // 3
        // 4: Проверяем, есть ли место для минимального смайлика ":-)" или ":-("
        if (i + 2 < n && input[i] == ':' && input[i + 1] == '-') {
            val bracket = input[i + 2] // 5

            // 6: Если за ":-" идет скобка
            if (bracket == ')' || bracket == '(') {
                i += 3 // 7: Пропускаем ":-" и первую скобку

                // 8: "Поедаем" все идущие подряд такие же скобки
                while (i < n && input[i] == bracket) {
                    i++
                }
                // Переходим к следующей итерации цикла while, не добавляя ничего в результат
                continue
            }
        }

        // 9: Если это не смайлик, добавляем текущий символ и идем дальше
        result.append(input[i])
        i++
    }


    return result.toString() // 10
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
                stack.addLast(a / b) // целочисленное деление с округлением к нулю
            }

            else -> stack.addLast(token.toInt())
        }
    }
    return stack.last()
}


fun buildArray(target: IntArray, n: Int): List<String> {

    val result = mutableListOf<String>()
    val resultArray = IntArray(target.size)
    var i = 0;
    var currentStack = 1
    while (true) {
        if (currentStack > n) {
            break
        }
        if (target.contentEquals(resultArray)) {
            break
        }
        result.add("Push")
        if (target[i] == currentStack) {
            resultArray[i] = currentStack
            i++

        } else {
            result.add("Pop")
        }
        currentStack++

    }

    return result
}


fun findDisappearedNumbers(nums: IntArray): List<Int> {
    val count = IntArray(nums.size + 1)
    for (i in nums) {
        count[i]++
    }
    val result = mutableListOf<Int>()
    for (i in nums) {
        if (count[i] == 0) {
            result.add(i)
        }
    }
    result.remove(0)
    return result

}

fun smallerNumbersThanCurrent(nums: IntArray): IntArray {

    val count = IntArray(101)
    for (i in nums) {
        count[i]++
    }
    val prefix = IntArray(101)
    for (i in 1..100) {
        prefix[i] = prefix[i - 1] + count[i - 1]
    }
    val result = IntArray(nums.size)
    for (i in nums.indices) {
        result[i] = prefix[nums[i]]
    }
    return result
}

fun getConcatenation(nums: IntArray): IntArray {
    val res = IntArray(nums.size * 2)
    for (i in nums.indices) {
        res[i] = nums[i]
        res[i + nums.size] = nums[i]
    }
    return res
}


fun findErrorNums(nums: IntArray): IntArray {
    val n = nums.size
    val count = IntArray(n + 1) // индекс от 1 до n
    for (num in nums) {
        count[num]++
    }
    var duplicate = 0
    var missing = 0
    for (i in 1..n) {
        if (count[i] == 2) duplicate = i
        if (count[i] == 0) missing = i
    }
    return intArrayOf(duplicate, missing)
}

fun findMaxConsecutiveOnes(nums: IntArray): Int {
    var topcount = 0
    var count = 0;
    for (i in nums.indices) {

        if (nums[i] == 1) {
            count++;
        }
        if (nums[i] != 1 || i == nums.lastIndex) {
            if (count >= topcount) {
                topcount = count

            }
            count = 0
        }

    }
    return topcount
}

fun shuffle(nums: IntArray, n: Int): IntArray {
    val res = IntArray(n * 2)
    var i = 0;
    repeat(n) {
        res[i] = nums[it]
        res[i + 1] = nums[n + it]
        i += 2
    }
    return res
}


fun isPalindrome(x: Int): Boolean {
    // Отрицательные числа и числа, оканчивающиеся на 0 (кроме самого 0) — не палиндромы
    if (x < 0 || (x % 10 == 0 && x != 0)) return false

    var num = x
    var reversed = 0

    // Переворачиваем вторую половину числа
    while (num > reversed) {
        reversed = reversed * 10 + num % 10
        num /= 10
    }

    // Для чётной длины: num == reversed
    // Для нечётной длины: num == reversed / 10 (отбрасываем среднюю цифру)
    return num == reversed || num == reversed / 10
}


fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>() // число - индекс
    nums.forEachIndexed { index, i ->
        if (map.containsKey(target - i)) {
            return intArrayOf(map[target - i]!!, index)
        } else map[i] = index
    }

    return intArrayOf()
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