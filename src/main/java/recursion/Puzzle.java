package recursion;

import java.util.*;

//数字猜谜 C-4.24
//https://github.com/wdlcameron/Solutions-to-Data-Structures-and-Algorithms-in-Python/blob/master/Chapter%204%20Exercises.ipynb
public class Puzzle {
    public static String a = "boy";
    public static String b = "girl";
    public static String c = "baby";
    static int count = 0;

    public static int str2Int(String text, Map<Character, Integer> res) {
        int length = text.length();
        int result = 0;
        for (int i = 0; i < length; i++) {
            result += res.get(text.charAt(i)) * Math.pow(10, length - i - 1);
        }
        return result;
    }

    public static void check(Map<Character, Integer> res) {
        if (str2Int(a, res) + str2Int(b, res) == str2Int(c, res)) {
            System.out.println(res);
            count++;
        }
    }

    //chars是所有的字母数组 k是字母数组的下标，res是字母对应的数字集合，nums是数字集合
    public static void resolve(List<Character> chars, int k, Map<Character, Integer> res, Set<Integer> nums) {
        if (k >= chars.size()) {
            check(res);
            return;
        }
        if (res.containsKey(chars.get(k))) {
            resolve(chars, k + 1, res, nums);
            return;
        }
        Set<Integer> allNums = new HashSet<>(nums);
        for (int num : allNums) {
            nums.remove(num);
            res.put(chars.get(k), num);
            resolve(chars, k + 1, res, nums);
            res.remove(chars.get(k));
            nums.add(num);
        }
    }

    public static void main(String[] args) {
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            nums.add(i);
        }
        count = 0;
        List<Character> characters = new ArrayList<>();
        a = "boy";
        b = "girl";
        c = "baby";
        characters.add('b');
        characters.add('o');
        characters.add('y');
        characters.add('g');
        characters.add('i');
        characters.add('r');
        characters.add('l');
        characters.add('b');
        characters.add('a');
        characters.add('y');
        Map<Character, Integer> res = new HashMap<>();
        Puzzle.resolve(characters, 0, res, nums);

        System.out.println("total count =" + count);

        for (int a = 3; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                if (a != b) {
                    for (int c = 0; c <= 9; c++) {
                        int num = a * 100 + b * 10 + c;
                        if (num > 316) {
                            int num_num = num * num;
                            int shiwan = num_num / 100000;
                            int wan = num_num / 10000 % 10;
                            int shi = num_num % 100 / 10;
                            int ge = num_num % 10;

                            if (wan == shi && a == shiwan && b == ge) {
                                System.out.println("a=" + a + ",b=" + b + ",c=" + c);
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }
    }

}
