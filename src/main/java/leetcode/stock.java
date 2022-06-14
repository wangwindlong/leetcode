package leetcode;

//股票问题 121 122 123 188 309 714
//https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/solution/by-zz1998-pbow/
//https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-wen-ti-by-chen-wei-f-gc4k/
public class stock {

    //121 s 只能买一次 卖一次, 在每一次的最低点买入，然后计算最大利润 每次取最大值
    public static int stock0(int[] prices) {
        int min = Integer.MAX_VALUE, max = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else if (price > min) max = Math.max(max, price - min);
        }
        return max;
    }

    //122 m 第一天买第二天卖 贪心算法
    public static int stock1(int[] prices) {
        int earn = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) earn += prices[i] - prices[i-1];
        }
        return earn;
    }

    public static void main(String[] args) {
        int[] datas = new int[] {2,4,1};
        System.out.println("只能买卖一次");
        System.out.println(stock.stock0(datas));
        System.out.println("当天买第二天卖");
        System.out.println(stock.stock1(datas));
    }
}
