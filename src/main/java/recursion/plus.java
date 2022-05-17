package recursion;

//只用加法和减法计算 m*n C4.12
public class plus {

    static int plus(int m, int n) {
        if (m >= n) return plus(m, n, 0);
        return plus(n, m, n - m);
    }

    static int plus(int m, int n, int cur) {
        if (cur == n) return 0;
        return plus(m, n, cur + 1) + m;
    }

    public static void main(String[] args) {
        System.out.println(plus.plus(10,5));
    }
}
