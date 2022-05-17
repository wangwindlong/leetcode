package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//求无重复子集的 元素集合的 所有子集合，数算C4.15 同leetcode 78题
public class subset {

    public static List<List<Integer>> sub(int[] datas) {
        List<List<Integer>> res = new ArrayList<>();
        sub(datas, res, new ArrayList<>(), 0);
        return res;
    }

    public static void sub(int[] datas, List<List<Integer>> res, List<Integer> tmp, int n) {
        if (n >= datas.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        sub(datas, res, tmp, n + 1);
        tmp.add(datas[n]);
        sub(datas, res, tmp, n + 1);
        tmp.remove(tmp.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(subset.sub(new int[]{1,2,3}).toArray()));
    }
}
