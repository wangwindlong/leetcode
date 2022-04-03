package leetcode;

import java.util.HashMap;
import java.util.Map;

public class sum1 {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int[] res = new int[2];
        for (int i = 0; i< length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int value = nums[i];
            if (map.containsKey(target - value)) {
                return new int[]{map.get(target - value), i};
            }
            map.put(value, i);
        }

        return new int[2];
    }

    public static void main(String[] args) {
        int[] res = new sum1().twoSum2(new int[]{1,2,3,5}, 4);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
