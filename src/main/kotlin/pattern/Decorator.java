package pattern;

public class Decorator implements ICar {
    private ICar car;

    public Decorator(ICar car) {
        this.car = car;
    }

    @Override
    public void show() {
        System.out.println("给汽车过户");
        System.out.println("给汽车上拍照");
        this.car.show();
        System.out.println("给汽车加装增程器");
    }
}

class Benz implements ICar {

    @Override
    public void show() {
        System.out.println("这是一辆崭新的奔驰汽车");
    }
}

interface ICar {
    public void show();
}


class Test{
    public static void main(String[] args) {
        ICar newcar = new Benz();
        ICar decorator = new Decorator(newcar);
        decorator.show();
    }
}