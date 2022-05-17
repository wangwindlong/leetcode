package leetcode;

//贪心算法，股票交易最大利润，只要后一天价格比前一天价格高就买入加卖出=后一天减前一天的值
public class m_stock_122_tanxin {
    public static int maxProfit(int[] prices) {
        int earn = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) earn += prices[i + 1] - prices[i];
        }
        return earn;
    }

    public static void main(String[] args) {
        System.out.println(m_stock_122_tanxin.maxProfit(new int[]{1, 2, 3, 4, 5}));
    }
}
