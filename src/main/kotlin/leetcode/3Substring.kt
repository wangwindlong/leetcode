package leetcode

import leetcode.substring.Companion.lengthOfLongestSubstring
import leetcode.substring.Companion.lengthOfLongestSubstring2
import leetcode.substring.Companion.lengthOfLongestSubstring3
import leetcode.substring.Companion.logestSubString
import kotlin.math.max

class substring {
    /**
     * Input: s = "abcabcbb"  Output: 3
     * 思路：双指针，都从第0个开始遍历，用128个int数组记录ascii码对应的最近出现索引位置
     * 如果数组中有记录则说明有重复，且如果索引(上一次出现位置)大于等于当前左指针位置，左指针在此基础上右移一位
     * 每次遍历都需要：对res 取 res和 右-左+1 的最大值，用来记录最长的值
     *               数组中记录右指针记录的索引值
     *               右指针往上次的索引右移一位
     */
    companion object {
        fun logestSubString(s: String) : Int {
            var res = 0
            var left = 0
            var right = 0
            val chars  = arrayOfNulls<Int>(128)
            for (c in s) {
                val index = chars[c.toInt()]
                if (index != null && index >= left && index < right) {
                    left = index + 1
                }
                res = Math.max(res, right - left + 1)
                chars[c.toInt()] = right
                right ++
            }

            return res
        }


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

        fun lengthOfLongestSubstring3(s: String): Int {
            var left = 0
            var right = 0
            var res = 0
            val chars: Array<Int?> = arrayOfNulls(128)
            for (i in s) {
                val index = chars[i.code]
                if (index != null && index >= left && left < right) {
                    left = index + 1
                }
                res = max(res, right - left + 1)
                chars[i.code] = right
                right++
            }
            return res
        }


    }

}

fun main() {
    println(logestSubString("aaaabbcccdd"))
}