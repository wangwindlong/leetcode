package leetcode;


import java.util.ArrayList;
import java.util.List;

public class ObserveTest {

    interface Clickable {
        void click();

        void register(Observer observer);

        void remove(Observer observer);
    }

    interface Observer {
        void onClick();
    }

    static class TextObserver implements Observer {

        @Override
        public void onClick() {
            System.out.println("文字改变");
        }
    }

    static class ColorObserver implements Observer {

        @Override
        public void onClick() {
            System.out.println("颜色改变");
        }
    }

    static class Button implements Clickable {
        List<Observer> observers = new ArrayList<>();

        @Override
        public void click() {
            for (Observer observer : observers) {
                observer.onClick();
            }
        }

        @Override
        public void register(Observer observer) {
            if (observers.contains(observer)) {
                observers.remove(observer);
            }
            observers.add(observer);
        }

        @Override
        public void remove(Observer observer) {
            observers.remove(observer);
        }
    }


    public static void main(String[] args) {
        Button button = new Button();
        button.register(new TextObserver());
        button.register(new ColorObserver());
        button.click();
    }
}
