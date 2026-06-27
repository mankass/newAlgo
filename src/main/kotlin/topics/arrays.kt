package org.example.topics

/**
 * Remove duplicates from sorted array in-place, return count of unique elements.
 * [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
 */
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

/**
 * Students in a queue either take the top sandwich or go to the back. Return count of students who can't eat.
 * [1700. Number of Students Unable to Eat Lunch](https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/)
 */
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

/**
 * Build target array using "Push"/"Pop" operations on a stream 1..n.
 * [1441. Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations/)
 */
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

/**
 * Find numbers missing from [1..n] in an array of length n.
 * [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)
 */
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

/**
 * For each element, count how many numbers in the array are smaller than it.
 * [1365. How Many Numbers Are Smaller Than the Current Number](https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/)
 */
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

/**
 * Return array that is the concatenation of nums with itself.
 * [1929. Concatenation of Array](https://leetcode.com/problems/concatenation-of-array/)
 */
fun getConcatenation(nums: IntArray): IntArray {
    val res = IntArray(nums.size * 2)
    for (i in nums.indices) {
        res[i] = nums[i]
        res[i + nums.size] = nums[i]
    }
    return res
}

/**
 * In array [1..n] one number is duplicated, one is missing. Find [duplicate, missing].
 * [645. Set Mismatch](https://leetcode.com/problems/set-mismatch/)
 */
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

/**
 * Find maximum number of consecutive 1's in a binary array.
 * [485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)
 */
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

/**
 * Shuffle array as [x1,y1,x2,y2,...,xn,yn] given nums = [x1..xn, y1..yn].
 * [1470. Shuffle the Array](https://leetcode.com/problems/shuffle-the-array/)
 */
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

/**
 * Find two indices that sum to target.
 * [1. Two Sum](https://leetcode.com/problems/two-sum/)
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    nums.forEachIndexed { index, i ->
        if (map.containsKey(target - i)) {
            return intArrayOf(map[target - i]!!, index)
        } else map[i] = index
    }
    return intArrayOf()
}
