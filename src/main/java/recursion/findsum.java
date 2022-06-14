package recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//找到两数之和为k的下标 C4.21复杂度O(N)
public class findsum {

    public static int[] findSum2(int[] datas, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < datas.length; i++) {
            int key = k - datas[i];
            if (map.containsKey(key)) return new int[] {map.get(key), i};
            map.put(datas[i], i);
        }
        return new int[] {-1, -1};
    }
    public static int[] findSum(int[] datas, int k, int index, Map<Integer, Integer> map) {
        if (index >= datas.length) return new int[] {-1, -1};
        int key = k - datas[index];
        if (map.containsKey(key)) {
            return new int[] {map.get(key), index};
        }
        map.put(datas[index], index);
        return findSum(datas, k, index + 1, map);
    }
    public static void main(String[] args) {
        int[] datas = new int[]{1,2,4,7,18,19, 6};
        System.out.println(Arrays.toString(findsum.findSum(datas, 10, 1, new HashMap<>())));
        System.out.println(Arrays.toString(findsum.findSum2(datas, 10)));

    }

}
