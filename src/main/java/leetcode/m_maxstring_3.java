package leetcode;

import java.util.HashMap;
import java.util.Map;

//最长不重复子串，使用滑动窗口
public class m_maxstring_3 {
    public static int maxString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        int start = 0; //记录上一次出现的位置的后一位
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) { //如果这个字母在前一次出现过，start移动到上次出现位置后一位
                //记录上一次出现位置的后一位和当前最大值，以免回头计算字母导致增加子串长度
                //abcadef  abcchda 这两种情况都适用
                //为什么这么做可以？因为start所指向的永远是无重复字符区间的开头
                start = Math.max(start, map.get(c));
            }
            map.put(c, i + 1);
            res = Math.max(res, i - start + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(m_maxstring_3.maxString("abcadef"));
        System.out.println(m_maxstring_3.maxString("abcchda"));
        System.out.println(m_maxstring_3.maxString("abccb"));
    }

}
