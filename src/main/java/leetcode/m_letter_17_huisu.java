package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class m_letter_17_huisu {
    static Map<Character, String> map = new HashMap<>();
    public static List<String> letters(String digits) {

        List<String> res = new ArrayList<>();
        generate("", 0, digits, res);
        return res;
    }

    public static void generate(String s, //每一个组合
                                int i, //level
                                String digits, List<String> res) {
        if (i == digits.length()) {
            res.add(s);
            return;
        }
        String letterMap = map.get(digits.charAt(i));
        for (int j = 0; j < letterMap.length(); j++) {
            generate(s + letterMap.charAt(j), i + 1, digits, res);
        }
    }

    public static void main(String[] args) {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        System.out.println(m_letter_17_huisu.letters("23"));
    }
}
