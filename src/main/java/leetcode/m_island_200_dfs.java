package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//岛屿问题，n*n网格中的岛屿个数
public class m_island_200_dfs {
    //沉没岛屿做法，dfs后把1置为0
    public static int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == '1'){
//                    dfs(grid, i, j);
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    //深度优先 3ms ?
    public static void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
    }
    //广度优先 7ms ?
    public static void bfs(char[][] grid, int i, int j) {
        Queue<int[]> res = new LinkedList<>();
        res.add(new int[] {i, j});
        while (!res.isEmpty()) {
            int[] item = res.remove();
            int row = item[0];
            int col = item[1];
            if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0') continue;
            grid[row][col] = '0';
            res.offer(new int[]{row, col+1});
            res.offer(new int[]{row, col-1});
            res.offer(new int[]{row-1, col});
            res.offer(new int[]{row+1, col});
        }
    }

    public static void main(String[] args) {
        char[][] chars = new char[3][5];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                chars[i][j] = '0';
            }
        }
        chars[0][1] = '1';
        System.out.println(m_island_200_dfs.numIslands(chars));
    }
}
