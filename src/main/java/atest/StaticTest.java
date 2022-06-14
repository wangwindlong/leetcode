package atest;

public class StaticTest {

    static {
        System.out.println("这里是静态代码区域");
    }

    static void test() {
        System.out.println("这里是测试");
    }

    public static void main(String[] args) {
//        StaticTest.test();
//        System.out.println(StaticTest.class);
    }
}
