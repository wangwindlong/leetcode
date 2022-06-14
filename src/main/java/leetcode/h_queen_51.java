package leetcode;

import java.util.*;

//8皇后问题，同一行列和两个斜对角不能放queen
public class h_queen_51 {

    public static List<List<String>> solveNQueens(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<List<String>> results = new ArrayList<>();

        dfs(res, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), n);
        System.out.println(Arrays.toString(res.toArray()));
        for (List<Integer> queens : res) {
            List<String> row = new ArrayList<>();
            for (int i : queens) {
                String item = "";
                for (int j = 0; j < n; j++) {
                    if (j == i) item += "Q";
                    else item += ".";
                }
                row.add(item);
            }
            results.add(row);
        }
        return results;
    }

    //queens保存每列的位置，值为[1,2,3] dif为45度线数组 值为[1,2,3]  sum为135度线
    public static void dfs(List<List<Integer>> res, List<Integer> queens, List<Integer> dif, List<Integer> sum, int n) {
        int count = queens.size();
        if (count == n) {
            res.add(new ArrayList<>(queens));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!queens.contains(i) && !dif.contains(count - i) && !sum.contains(count + i)) {
                queens.add(i);
                dif.add(count - i);
                sum.add(count + i);
                dfs(res, queens, dif, sum, n);
                queens.remove(queens.size() - 1);
                dif.remove(dif.size() - 1);
                sum.remove(sum.size() - 1);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(h_queen_51.solveNQueens(4).toArray()));
    }


    /**
     * python 解法 https://leetcode.com/problems/n-queens/discuss/19810/Fast-short-and-easy-to-understand-python-solution-11-lines-76ms
     * def solveNQueens(self, n):
     *         def DFS(queens, xy_dif, xy_sum):
     *             p = len(queens)
     *             if p==n:
     *                 result.append(queens)
     *                 return None
     *             for q in range(n):
     *                 if q not in queens and p-q not in xy_dif and p+q not in xy_sum:
     *                     DFS(queens+[q], xy_dif+[p-q], xy_sum+[p+q])
     *         result = []
     *         DFS([],[],[])
     *         return [["."*i + "Q" + "."*(n-i-1) for i in sol] for sol in result]
     */
}
