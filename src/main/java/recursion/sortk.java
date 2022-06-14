package recursion;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//排序数组，使得所有小于等于k的在左边，其他在右边 C-4.20
public class sortk {
    public static Integer[] sortK2(int[] datas, int k, int cur, Deque<Integer> res) {
        if (cur >= datas.length) return res.toArray(new Integer[0]);
        if (datas[cur] <= k) res.offerFirst(datas[cur]);
        else res.offerLast(datas[cur]);
        return sortK2(datas, k, cur + 1, res);
    }

    public static Integer[] sortK(int[] datas, int k) {
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < datas.length; i++) {
            if (datas[i] <= k) queue.addFirst(datas[i]);
            else queue.addLast(datas[i]);
        }
        return queue.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        int[] datas = new int[]{5,6,199,39,10,14, 19, 20,8};
        System.out.println(Arrays.toString(sortk.sortK(datas, 16)));
        System.out.println(Arrays.toString(sortk.sortK2(datas, 16, 0 ,new LinkedList<>())));

    }

}
