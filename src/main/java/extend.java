import java.util.ArrayList;
import java.util.List;

public class extend {

    static class Human {

    }

    static class Father extends Human {

    }
    static class Child extends Father {

    }

    public static void main(String[] args) {
//        List<? extends Father> list1 = new ArrayList<>(); //上界  不能往里存，只能往外取
//        List<? super Father> list2 = new ArrayList<>();  //下界 可以存子类，但是获取不到子类类型
//        list1.add(new Father()); //error
//        list1.add(new Child());  //error
//        list2.add(new Father());
//        list2.add(new Child());
//
//        Father a = list1.get(0);
//        Human b = list1.get(0);
//        Child c = list1.get(0);  //error
//
//        Father a1 = list2.get(0); //error
//        Human b1 = list2.get(0);  //error
//        Child c1 = list2.get(0);  //error

    }
}
