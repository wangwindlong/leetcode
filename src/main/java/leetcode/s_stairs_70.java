package leetcode;

public class s_stairs_70 {
    //爬楼梯问题 简单 70题
    public static int climbStairs(int n) {
//        return fab(n, 1, 1);
        return climb(n);
    }

    //使用尾递归 38,3M
    private static int fab(int n, int first, int second) {
        if (n <= 1) return second;
        return fab(n - 1, second, second + first);
    }

    //使用循环 37.7M
    private static int climb(int n) {
        int a = 1, b = 1, res = 1;
        for (int i = 1; i < n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

    //3步台阶问题 https://leetcode.cn/problems/three-steps-problem-lcci/solution/by-sparkk-avzk
    private static int fab3(int n) {
        int[] res = new int[4]; //存储前3次的结果，最后一个是结果
        if (n <= 2) return n;
        else if (n == 3) return 4;
        res[0] = 0;
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i < n; i++) {
            res[3] = res[0] + res[1] + res[2];
            res[0] = res[1];
            res[1] = res[2];
            res[2] = res[3];
        }
        return res[3];
    }
    //3阶台阶，相邻不能相同？

    public static void main(String[] args) {
        System.out.println(s_stairs_70.climbStairs(4)); //5 O(N)
        System.out.println("3阶台阶");
        System.out.println(s_stairs_70.fab3(4)); //5 O(N)
        System.out.println(s_stairs_70.fab3(5)); //5 O(N)
    }
}
