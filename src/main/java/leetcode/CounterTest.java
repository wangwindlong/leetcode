package leetcode;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CounterTest {

    private static long times = 1000000000L;
    private static int threadCount = 16;

    private static long c_1 = 0L;

    private static AtomicLong c_2 = new AtomicLong();

    private static LongAdder c_3 = new LongAdder();

    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        List<Thread> ts = IntStream
                .range(0, threadCount)
                .mapToObj(i -> new Thread(CounterTest::f1))
                .collect(Collectors.toList());

        ts.parallelStream().forEach(thread -> {
            thread.start();
            System.err.println("start thread: " + thread.getName());
        });
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.err.println(("单线程执行累加次数: " + times + ", 共耗时: ") + (System.currentTimeMillis() - t) + "ms");
        System.err.println(c_1);
        System.err.println(c_2.get());
        System.err.println(c_3.longValue());

    }

    private synchronized static void f1() {
//    private    static void f1() {
        for (long i = 0; i < times; i++) {
            c_1++;
        }
    }

    private static void f2() {
        for (long i = 0; i < times; i++) {
            c_2.getAndIncrement();
        }
    }

    private static void f3() {
        for (long i = 0; i < times; i++) {
            c_3.add(1L);
        }
    }
}
