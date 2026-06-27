package org.example.topics

/**
 * Check if integer is a palindrome (reads the same forward and backward).
 * [9. Palindrome Number](https://leetcode.com/problems/palindrome-number/)
 */
fun isPalindrome(x: Int): Boolean {
    if (x < 0 || (x % 10 == 0 && x != 0)) return false

    var num = x
    var reversed = 0

    while (num > reversed) {
        reversed = reversed * 10 + num % 10
        num /= 10
    }

    return num == reversed || num == reversed / 10
}
