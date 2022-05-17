package leetcode;

//中等题 Pow(x,n) 计算x 的n 次方，n可以为负数
public class m_pow_50 {

    public static double myPow(double x, int n) {
        int newN = n;
        double newX = x;
        if (n < 0) {
            newX = 1 / x;
            newN = -n;
        }
        return _myPow(newX, newN);
    }

    //分治法 O(logn) / O(logn)
    private static double _myPow(double x, int n) {
        if (n == 0) return 1.0; //循环终止条件
        double res = _myPow(x, n / 2); //n/2处理条件，分成子问题
        return res * res * (n % 2 != 0 ? x : 1); //根据子问题的答案进行处理
    }

    //快速幂，牛顿迭代法 数学推导 O(logn) / O(1)

    public static void main(String[] args) {
        System.out.println(m_pow_50.myPow(10, 2));
        System.out.println(m_pow_50.myPow(10, -2));
        System.out.println(m_pow_50.myPow(2, -2));
    }
}
