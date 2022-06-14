package leetcode;

import java.util.Arrays;

//朋友圈或者省份个数，给定一个二维数组，相邻的为一个朋友圈或者一个省，斜对角线对称
public class m_province_547_trie {

    public static int fincNum(int[][] datas) {
        int cities = datas.length;
        boolean[] isVisited = new boolean[cities];
        int count = 0;
        for (int i = 0; i < cities; i++) {
            if (!isVisited[i]) {
                dfs(datas, i, isVisited);
                count++;
            }
        }
        return count;
    }
    //深度优先
    public static void dfs(int[][] datas, int row, boolean[] isVisited) {
        for (int i = 0; i < datas.length; i++) {
            if (datas[row][i] == 1 && !isVisited[i]) {
                isVisited[i] = true;
                dfs(datas, i, isVisited);
            }
        }
    }
    public static void main(String[] args) {
        int count = 5;
        int[][] datas = new int[count][count];
        int last = 0;
        for (int i = 0; i < count; i++) {
            datas[i][i] = 1;
            for (int j = 0; j < count; j++) {
                if (i != j) {
                    datas[i][j] = (last & 1) == 0 ? 1: 0;
                    datas[j][i] = (last & 1) == 0 ? 1: 0;
                }
                last++;
            }
            System.out.println(Arrays.toString(datas[i]));
        }

        System.out.println(m_province_547_trie.fincNum(datas));

    }

}
