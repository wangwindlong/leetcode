package leetcode;

import java.util.Arrays;

//路径问题，中间有障碍物，与62题有差异，只初始化第一项，从0遍历，只有当第二列才累加，第一列不累加
public class m_Path_63_dp {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[] res = new int[col];
        res[0] = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] != 0) res[j] = 0;
                else if (j > 0) res[j] += res[j - 1];
            }
        }
        System.out.println(Arrays.toString(res));
        return res[col-1];
    }

    public static int path(int[][] obstacleGrid) {
        int[] res = new int[obstacleGrid[0].length];
        res[0] = 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) res[j] = 0;
                else if (j > 0) res[j] += res[j-1];
            }
        }
        System.out.println(Arrays.toString(res));
        return res[res.length - 1];
    }

    public static void main(String[] args) {
        int[][] nums = new int[3][3];
        nums[1][1] = 1;
        System.out.println(m_Path_63_dp.path(nums));
        System.out.println(m_Path_63_dp.uniquePathsWithObstacles(nums));
        nums = new int[2][2];
        nums[0][1] = 1;
        System.out.println(m_Path_63_dp.uniquePathsWithObstacles(nums));
    }
}
