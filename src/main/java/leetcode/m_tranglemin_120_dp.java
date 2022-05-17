package leetcode;

import java.util.ArrayList;
import java.util.List;

public class m_tranglemin_120_dp {
    //倒序dp
    public static int minimumTotal(List<List<Integer>> triangle) {
        int[] res = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(2);
        ArrayList<Integer> a2 = new ArrayList<>();
        a2.add(3);
        a2.add(4);
        ArrayList<Integer> a3 = new ArrayList<>();
        a3.add(6);
        a3.add(5);
        a3.add(7);
        ArrayList<Integer> a4 = new ArrayList<>();
        a4.add(4);
        a4.add(1);
        a4.add(8);
        a4.add(3);
        triangle.add(a1);
        triangle.add(a2);
        triangle.add(a3);
        triangle.add(a4);
        System.out.println(m_tranglemin_120_dp.minimumTotal(triangle));
    }
}
