package recursion;

public class power {

    public static int power2(int x, int n) {
        if (n == 0) return 1;
        int index = 1;
        int prev = x;
        while (index <= n/2) {
            prev = prev * prev;
            index = index * 2;
        }
        if ((n & 1) != 0) prev *= x;
        return prev;
    }

    public static int power(int x, int n) {
        if (n == 0) return 1;
        int c = power(x, n/2);
        if ((n & 1) == 0) {
            return c * c;
        } else {
            return x * c * c;
        }
    }

    public static void main(String[] args) {
        System.out.println(power.power2(2, 5));
    }

}
