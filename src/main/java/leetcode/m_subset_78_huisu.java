package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//回溯算法，获取数组nums的所有子集
public class m_subset_78_huisu {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
//        subset(nums, res); //迭代法 数学推导
//        backtrack(0, nums, res, new ArrayList()); //回溯法-递归实现
        sub(0, nums, res, new ArrayList<>()); //回溯法-递归实现
        return res;
    }

    //迭代法, 有 2^n 种组合，每个位置的数字都有可能在或不在集合里，所以从0到2^n数字里遍历只要有满足1-n位不为0，就把该下标的元素放入子集
    private static void subset(int[] nums, List<List<Integer>> res) {
        int n = nums.length;
        List<Integer> tmp;
        for (int i = 0; i < 1 << n; i++) {
            tmp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & 1 << j) != 0) tmp.add(nums[j]);
            }
            res.add(tmp);
        }
    }

    //回溯法
    private static void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            backtrack(j + 1, nums, res, tmp);
            System.out.println(Arrays.toString(tmp.toArray()));
            tmp.remove(tmp.size() - 1);
        }
    }

    //回溯法2
    private static void sub(int cur, int[] nums, List<List<Integer>> res, List<Integer> tmp) {
        if (cur == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        sub(cur + 1, nums, res, tmp);
        tmp.add(nums[cur]);
        sub(cur + 1, nums, res, tmp);
        tmp.remove(tmp.size() - 1);
    }


    //参考 https://leetcode.cn/problems/subsets/solution/hui-su-suan-fa-by-powcai-5/
    public static void main(String[] args) {
        System.out.println(Arrays.toString(m_subset_78_huisu.subsets(new int[] {1, 2, 3}).toArray()));
    }






























}
