package leetcode;

import java.util.HashMap;

public class s_anagram_242 {


    //使用char 26个数组保存出现频次
    boolean isValid(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] chars = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            chars[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            int count = chars[s2.charAt(i) - 'a']--;
            if (count < 1) return false;
        }
        return true;
    }


    //使用哈希表
    boolean isValid2(String s1, String s2) {
        HashMap<Character, Integer> map = new HashMap<>();
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++) {
            Character c = s1.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s2.length(); i++) {
            Integer count = map.getOrDefault(s2.charAt(i), 0);
            map.put(s2.charAt(i), --count); //将减一以后的值写入hashmap
            if (count < 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        s_anagram_242 anagram = new s_anagram_242();
        boolean isValid = anagram.isValid("anagram", "nagrama");
        System.out.println(isValid);
    }
}
