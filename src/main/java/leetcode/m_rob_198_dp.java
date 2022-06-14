package leetcode;

//隔间房盗窃
public class m_rob_198_dp {

    //使用动态规划，根据前面一家抢 和 前面一家不抢的最大值来保存
    public static int rob(int[] datas) {
        int n = datas.length;
        if (n < 2) return datas[0];
        if (n == 2) return Math.max(datas[0], datas[1]);
        int[] res = new int[n];
        res[0] = datas[0];
        res[1] = datas[1];
//        int prev = datas[0], cur = Math.max(datas[0], datas[1]);
        for (int i = 2; i < n; i++) {
            res[i] = Math.max(res[i - 1], res[i - 2] + datas[i]);
        }
        return res[n - 1];
    }

    //使用滚动数组
    public static int rob2(int[] datas) {
        int prev = 0, cur = 0;
        for (int data : datas) {
            int tmp = cur; //缓存上一次的最新值
            //获取前前一次的值加上当前值，和上一次的值 最大值
            cur = Math.max(prev + data, cur);
            //上上一次的值更新为上一次的值
            prev = tmp;
        }
        return cur;
    }

    public static void main(String[] args) {
        int[] datas = new int[]{2, 1, 3, 1};
        System.out.println(m_rob_198_dp.rob2(datas));

    }

}
