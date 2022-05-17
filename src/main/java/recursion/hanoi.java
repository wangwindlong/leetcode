package recursion;

public class hanoi {

    public static void hanoi(int n, char X, char Y, char Z) {
        if (n == 1) {
            move(n, X, Z);
            return;
        }
        hanoi(n - 1, X, Z, Y);
        move(n, X, Z);
        hanoi(n - 1, Y, X, Z);
    }

    static void move(int n, char from, char target) {
        System.out.println("移动第 " + n + " 块盘子，从" + from + "到" + target);
    }

    public static void main(String[] args) {
        hanoi(3, 'X', 'Y', 'Z');
    }
}
