package leetcode;

//两个字符串最大公共子序列
public class m_submax_1143_dp {
    //动态规划 注意这里数组长度+1，遍历时从1开始，取字符从i-1开始，两串字符组成一个二维数组
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] res = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1))
                    //最后一个字母相同，则等于两个字符串各自前一格的最大长度+1
                    res[i][j] = res[i-1][j-1] + 1;
                //如果最后一个字符不一样，则取各自前一个和对方当前长度的最大公共子序列
                else res[i][j] = Math.max(res[i-1][j], res[i][j-1]);
            }
        }
        return res[m][n];
    }
    //深度优先搜索 dfs？

    public static void main(String[] args) {
        System.out.println(m_submax_1143_dp.longestCommonSubsequence("abcde", "ace"));
    }
}
