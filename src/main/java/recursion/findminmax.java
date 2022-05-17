package recursion;

import java.util.Arrays;

//找出数组中的最大和最小值 python 数算 习题C4.9
public class findminmax {

    public static int[] findMinMax(int[] datas) {
        return findMinMax(datas, 0, datas.length - 1);
    }

    public static int[] findMinMax(int[] datas, int start, int end) {
        int left = datas[start];
        int right = datas[end];
        if (left > right) {
            int tmp = right;
            right = left;
            left = tmp;
        }
        if (start >= end - 1) return new int[]{left, right};
        int[] res = findMinMax(datas, start + 1, end - 1);
        return new int[]{Math.min(res[0], left), Math.max(res[0], right)};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findminmax.findMinMax(new int[]{1, 4, 6, -10, 20})));
    }
}
