package leetcode;

import java.util.Arrays;

//排序，奇数索引上放置奇数，偶数位上放偶数
public class s_sortodd_922 {

    public static int[] sort(int[] nums) {
        int[] res = new int[nums.length];
        int index = 0, index2 = 1;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 0) {
                res[index] = nums[i];
                index += 2;
            } else {
                res[index2] = nums[i];
                index2 += 2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] datas = new int[]{1, 4, 6, 3, 8, 11, 66, 13};
        System.out.println(Arrays.toString(s_sortodd_922.sort(datas)));

    }

}
