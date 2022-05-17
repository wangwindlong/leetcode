package leetcode;

import java.util.Arrays;

//路径规划，机器人从m * n的网格左上角到右下角的路径，只能向右或向下
public class m_Path_62_dp {

    public static int uniquePaths(int m, int n) {
        if (n <= 0) return 0;
        int[][] cur = new int[m][n];
        for (int i = 0; i < n; i++) cur[0][i] = 1;
        for (int i = 0; i < m; i++) cur[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[i][j] = cur[i][j - 1] + cur[i-1][j];
            }
        }
        return cur[m-1][n-1];
    }

    //将二维数组扁平化到一维数组，每次叠加上一次的次数
    //https://leetcode.com/problems/unique-paths/discuss/22954/C%2B%2B-DP
    public static int uniquePaths2(int m, int n) {
        if (n <= 0) return 0;
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                System.out.println("before cur[j]="+cur[j]);
                cur[j] += cur[j - 1];//此处为什么是j 而不是i ？
                System.out.println("after cur[j]="+cur[j]);
            }
        }
        return cur[n - 1];
    }

    //逆推 从终点到起点的路径数，需要知道起点第一个斜对角的路径数然后依次类推到终点
    public static int uniquePaths3(int m, int n) {
        if (n <= 0) return 0;
        int[][] cur = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cur[i][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                System.out.println("uniquePaths3 before cur[j]="+Arrays.toString(cur[i]) +",cur[i][j]="+cur[i][j]+",i="+i + ",j="+j);
                cur[i][j] = cur[i-1][j] + cur[i][j - 1];
            }
        }
        return cur[m-1][n - 1];
    }

    //正推，需要从终点前两个节点来 算出斜对角线格子到终点的路径数
    public static int uniquePaths4(int m, int n) {
        if (n <= 0) return 0;
        int[][] cur = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cur[i][j] = 1;
            }
        }
        for (int i = m-2; i >=0; i--) {
            for (int j = n-2; j >= 0; j--) {
                cur[i][j] = cur[i][j+1] + cur[i+1][j];
            }
        }
        return cur[0][0];
    }

    //正推同上，仅保留两个一维数组，因为每次递归只用到了两个值：cur[i][j+1]当前行 + cur[i+1][j]下一行
    public static int uniquePaths5(int m, int n) {
        if (n <= 0) return 0;
        int[] cur = new int[n];
        int[] pre = new int[n];
        Arrays.fill(cur, 1);
        Arrays.fill(pre, 1);
        for (int i = m-2; i >=0; i--) {
            for (int j = n-2; j >= 0; j--) {
                cur[j] = pre[j] + cur[j+1];
            }
            pre = cur;
        }
        return cur[0];
    }

    //正推同上，仅保留一个一维数组，因为每次pre[j] 其实是上一次cur更新前的数值
    public static int uniquePaths6(int m, int n) {
        if (n <= 0) return 0;
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = m-2; i >=0; i--) {
            for (int j = n-2; j >= 0; j--) {
                cur[j] = cur[j] + cur[j+1];
            }
        }
        return cur[0];
    }

    public static void main(String[] args) {
        System.out.println(m_Path_62_dp.uniquePaths3(4, 7));
        System.out.println(m_Path_62_dp.uniquePaths4(4, 7));
        System.out.println(m_Path_62_dp.uniquePaths5(4, 7));
        System.out.println(m_Path_62_dp.uniquePaths6(4, 7));
    }

}
