package leetcode;

import java.util.*;

// 给定一个数组，求出最少子元素组合成 target面值
public class m_coinchange_322_dp {
    //bfs效率很慢，会超时，如何优化？记忆化？
    public static int coinchange(int[] datas, int amount) {
        if (datas.length == 0) return 0;
        if (amount == 0) return 0;
        int[][] visited = new int[amount][datas.length];
        Queue<Integer> list = new LinkedList<>();
        int count = 0;
        list.offer(amount);
        while (!list.isEmpty()) {
            int queuesize = list.size();
            for (int i = 0; i < queuesize; i++) {
                int change = list.poll();
                for (int item : datas) {
                    if (change < item) continue;
                    if (change == item) return count + 1;
                    list.offer(change - item);
                }
            }
            count++;
        }
        return -1;
    }

    //动态规划,参考爬楼梯 k个步数,计算最少步数到达amount
    //如要知道5的最小硬币数，[5,[4,3,2,1,0]] 要知道5就得知道5-k的个数+1 且取最小值
    public static int coinchangeDp(int[] datas, int amount) {
        if (datas.length == 0) return 0;
        if (amount == 0) return 0;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);//用来作为边界条件，且最后返回时作为-1的判断
        dp[0] = 0;//初始化第一个为0，很重要，因为是从0开始增加往上迭代到amount，如果没有起始那么就没有以下条件的开始
        for (int i = 1; i <= amount; i++) {
            for (int data : datas) {
                if (data <= i) { //当当前总数大于钱币面额时进入判断
                    //当前的次数和第k个台阶前的次数+1比 取最小值，如果没有则当前值还是amount+1
                    dp[i] = Math.min(dp[i], dp[i - data] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] datas = new int[]{1,2,5};
        System.out.println(m_coinchange_322_dp.coinchangeDp(datas, 12));
         
    }
    
}
