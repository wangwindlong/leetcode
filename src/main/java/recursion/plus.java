package recursion;

//只用加法和减法计算 m*n C4.12
public class plus {

    //二分递归 复杂度O(logN)
    static int plus2(int m, int n) {
        if (m >= n) return _plus2(m, n);
        return _plus2(n, m);
    }

    static int _plus2(int m, int n) {
        if (n <= 0) return 0;
        int prev = _plus2(m,n >> 1);
        if ((n & 1) == 0) {
            prev += prev;
        } else {
            prev += prev + m;
        }
        return prev;
    }

    //快速倍乘
    static int plus3(int m, int n) {
        int res = 0;
        while (n > 0) {
            if ((n & 1) == 1) res += m;
            n >>= 1;
            m += m;
        }
        return res;
    }

    //普通递归，复杂度O(n)
    static int plus(int m, int n) {
        if (m >= n) return plus(m, n, 0);
        return plus(n, m, 0);
    }

    static int plus(int m, int n, int cur) {
        if (cur == n) return 0;
        return plus(m, n, cur + 1) + m;
    }

    public static void main(String[] args) {
        System.out.println(plus.plus(5,10));
        System.out.println(plus.plus2(10,5));
        System.out.println(plus.plus3(10,5));
    }
}
