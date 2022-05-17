public class hanoi {
    static void change(int n, char X, char Y, char Z) {
        if (n == 1) {
            move(1, X, Z);
            return;
        }
        change(n - 1, X, Z, Y);
        move(n, X, Z);
        change(n - 1, Y, X, Z);
    }

    static void move(int n, char X, char Y) {
        System.out.println("第"+n+ "个盘子 从"+X+"挪到"+Y);
    }

    public static void main(String[] args) {
        hanoi.change(2, 'x', 'y', 'z');
    }
    //todo 怎么使用非递归实现？

}
