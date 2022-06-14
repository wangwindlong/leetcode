package atest;

public class Extends {

    static class Base {
        public Base() {
            System.out.println("base init");
        }
    }

    static class Son extends Base {
        public Son() {
            System.out.println("son init");
        }
    }

    public static void main(String[] args) {
        Son son = new Son();

    }
}
