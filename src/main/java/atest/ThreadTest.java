package atest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    public static void main(String[] args) {
        ThreadLocal<Integer> local = new InheritableThreadLocal<>();
        local.set(5);
        Thread thread = new Thread() {
            @Override
            public void run() {
                int count = 0;
                while (count < 10) {
                    local.set(local.get() + 1);
                    if (interrupted()) {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(count++);
                }
            }
        };
        thread.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("您好 当前local = " + local.get());
            }
        };

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(runnable);
        executor.shutdown();
    }
}
