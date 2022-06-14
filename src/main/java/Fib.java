import java.util.Arrays;

public class Fib {
    int prev = 1, current = 1;

    //70爬楼梯 https://leetcode-cn.com/problems/climbing-stairs/solution/
    // O(n)  + O(n)
    public int climbStairs(int n) {
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            result[i] = result[i-1] + result[i-2];
        }
        return result[n];
    }

    //简化数组
    public int _climbStairs(int n) {
        int prev = 0, cur = 1;
        for (int i = 1; i < n; i++) {
            int tmp = cur;
            cur = prev + cur;
            prev = tmp;
        }
        return cur;
    }

    //O(n) + O(1)
    public int climbStairs3(int n) {
        int result = 1;
        for (int i = 2; i < n + 1; i++) {
            result = prev + current;
            prev = current;
            current = result;
        }
        return result;
    }

    /* 动态规划五部曲：
     * 1.确定dp[i]的下标以及dp值的含义： 爬到第i层楼梯，有dp[i]种方法；
     * 2.确定动态规划的递推公式：dp[i] = dp[i-1] + dp[i-2];
     * 3.dp数组的初始化：因为提示中，1<=n<=45 所以初始化值，dp[1] = 1, dp[2] = 2;
     * 4.确定遍历顺序：分析递推公式可知当前值依赖前两个值来确定，所以递推顺序应该是从前往后；
     * 5.打印dp数组看自己写的对不对；
     */
    public int climbStairs_dp(int n) {
        if (n <= 1) return n;
        /* 定义dp数组 */
        int[] dp = new int[n+1];
        /* 初始化dp数组 */
        dp[1] = 1;
        dp[2] = 2;
        /* 从前往后遍历 */
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    public int climbStairs_dp2(int n) {
        if (n <= 1) return n;
        /* 定义dp数组 */
        int prev = 1, cur = 2;
        /* 从前往后遍历 */
        for(int i = 3; i <= n; i++) {
            int tmp = cur;
            cur = prev + cur;
            prev = tmp;
        }
        return cur;
    }

    public int climbStairs4(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) Math.round(fibn / sqrt5);
    }

    public Fib(int first, int second) {
        prev = first;
        current = second;
    }

    public void fib() {
        int res = prev + current;
        System.out.println(res);
        prev = current;
        current = res;
    }

    //非递归
    public int[] getfib(int n) {
        int[] result = new int[Math.max(n, 2)];
        result[0] = prev;
        result[1] = current;
        for (int i = 2; i < n; i++) {
            result[i] = current + prev;
            prev = current;
            current = result[i];
        }
        return result;
    }

    public static int fib(int n, int prev, int last) {
        if (n <= 1) return prev;
        return fib(n-1, last, prev + last);
    }

    public static void main(String[] args) {
        Fib fib = new Fib(0, 1);
//        for (int i = 0; i < 40; i++) {
//            fib.fib();
//        }
        System.out.println(Arrays.toString(fib.getfib(2)));
        System.out.println(fib._climbStairs(3));
        System.out.println(Fib.fib(12, 0, 1));
        System.out.println(fib.climbStairs_dp2(4));
    }
}
