package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class m_kuohao_22 {

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
//        generateAll(new char[2 * n], 0, combinations);
        test(0, 0, n, "", combinations);
        return combinations;
    }

    //使用二路递归 binary recursion 2^n 复杂度
    public void test(int left, int right, int n, String s, List<String> result) {
        if (left >= n && right >= n) {
            result.add(s);
            return;
        }
        if (left < n) test(left + 1, right, n, s + "(", result);
        if (right < left) test(left, right+1, n, s + ")", result);
    }

    //暴力求解，复杂度是
    public static void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
//            if (valid(current)) {
                result.add(new String(current));
//            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public static boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    public static void main(String[] args) {
        ArrayList<String> res = new ArrayList<>();
        m_kuohao_22.generateAll(new char[3], 0, res);
        System.out.println(Arrays.toString(res.toArray()));
        m_kuohao_22 kuo = new m_kuohao_22();
        System.out.println(Arrays.toString(kuo.generateParenthesis(3).toArray()));

    }
}
