package recursion;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

//排序 使得所有偶数在所有奇数前面 C4.19
public class sortodd {
    public static Integer[] sortOdd(int[] datas) {
        return sortOdd(datas, 0, new LinkedList<>());
    }
    public static Integer[] sortOdd(int[] datas, int cur, Deque<Integer> res) {
        if (cur >= datas.length) return res.toArray(new Integer[0]);
        if ((datas[cur] & 1) == 0) res.offerFirst(datas[cur]);
        else res.offerLast(datas[cur]);
        return sortOdd(datas, cur + 1, res);
    }
    public static void main(String[] args) {
        int[] datas = new int[]{2,12,14,3,4,5,6,7,8, 31, 50, 55, 66,53,52,54,56,58};
        System.out.println(Arrays.toString(sortodd.sortOdd(datas)));

    }

}
