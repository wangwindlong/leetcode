package leetcode

import leetcode.substring.Companion.lengthOfLongestSubstring
import leetcode.substring.Companion.lengthOfLongestSubstring2
import kotlin.math.max

class substring {
    companion object {
        fun lengthOfLongestSubstring(s: String): Int {
            val chars = arrayOfNulls<Int>(128)
            var left = 0
            var right = 0
            var res = 0
            for (c in s) {
                val index = chars[c.code]
                if (index != null && index >= left && index < right) {
                    left = index + 1
                }
                res = max(res, right - left + 1)
                chars[c.code] = right
                right++
            }
            return res
        }




        fun lengthOfLongestSubstring2(s: String): Int {
            val chars = arrayOfNulls<Int>(128)
            var left = 0
            var right = 0
            var res = 0

            for (c in s) {
                val index = chars[c.code]
                if (index != null && index >= left && index < right) {
                    left = index + 1
                }
                res = max(res, right - left + 1)
                chars[c.code] = right
                right++
            }
            return res
        }


    }






}

fun main() {
    println(lengthOfLongestSubstring2("aaaabbcccddd"))
}