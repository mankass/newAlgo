package org.example

fun removeDuplicates(nums: IntArray): Int {
    var prevValue = nums.first()
    var countUnique = 1
    for (i in 1 until nums.size) {
        if (nums[i] > prevValue) {
            nums[countUnique] = nums[i]
            countUnique += 1
            prevValue = nums[i]
        }
    }
    return countUnique
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

fun buildArray(target: IntArray, n: Int): List<String> {
    val result = mutableListOf<String>()
    val resultArray = IntArray(target.size)
    var i = 0
    var currentStack = 1
    while (true) {
        if (currentStack > n) break
        if (target.contentEquals(resultArray)) break
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
    val count = IntArray(n + 1)
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
    var count = 0
    for (i in nums.indices) {
        if (nums[i] == 1) {
            count++
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
    var i = 0
    repeat(n) {
        res[i] = nums[it]
        res[i + 1] = nums[n + it]
        i += 2
    }
    return res
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    nums.forEachIndexed { index, i ->
        if (map.containsKey(target - i)) {
            return intArrayOf(map[target - i]!!, index)
        } else map[i] = index
    }
    return intArrayOf()
}
