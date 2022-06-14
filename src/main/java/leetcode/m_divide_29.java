package leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.cn/problems/divide-two-integers/solution/liang-shu-xiang-chu-by-leetcode-solution-5hic/
//不用除法和乘法计算除法
public class m_divide_29 {
    public static int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(divisor);
        int index = 0;
        // 注意溢出
        while (candidates.get(index) >= dividend - candidates.get(index)) {
            candidates.add(candidates.get(index) + candidates.get(index));
            ++index;
        }
        int ans = 0;
        for (int i = candidates.size() - 1; i >= 0; --i) {
            if (candidates.get(i) >= dividend) {
                ans += 1 << i;
                dividend -= candidates.get(i);
            }
        }

        return rev ? -ans : ans;
    }

    public static int divide2(int dividend, int divisor) {
        boolean fu = (((dividend >>> 31) ^ (divisor >>> 31)) == 1);
        if(dividend > 0) dividend = -dividend;
        if(divisor > 0) divisor = -divisor;
        int mod = divisor;
        int minn = dividend >> 1;
        int now = -1;
        while(mod >= minn && mod >= (Integer.MIN_VALUE >> 1)) {
            mod <<= 1;
            now <<= 1;
        }
        int ans = 0;
        while(dividend <= divisor){
            while(mod < dividend){
                mod >>= 1;
                now >>= 1;
            }
            while(dividend <= mod) {
                dividend -= mod;
                ans-=now;
            }
        }
        if(ans == -2147483648 && !fu) return 2147483647;
        return fu?-ans:ans;
    }

    public static void main(String[] args) {
        System.out.println(m_divide_29.divide(100, 2));
        System.out.println(m_divide_29.divide2(100, 2));
    }
}
