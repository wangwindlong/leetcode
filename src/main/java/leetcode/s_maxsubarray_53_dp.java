package leetcode;

//给定一个数组 最大连续子序列的和
public class s_maxsubarray_53_dp {

    //最大子序和 = 当前元素自身 或者前一个元素的子集合序列和的最大值
    //f(i)=max{f(i−1)+nums[i],nums[i]}
    public static int maxSub(int[] datas) {
        int res = datas[datas.length - 1];
        int prev = 0;
        for (int i = datas.length - 2; i >= 0; i--) {
            prev = Math.max(prev + datas[i], datas[i]);
            res = Math.max(res, prev);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] datas = new int[]{-2, -1, -3, 4, -1, 2, 1, -5, 4}; //6
        System.out.println(s_maxsubarray_53_dp.maxSub(datas));
    }
    
}
