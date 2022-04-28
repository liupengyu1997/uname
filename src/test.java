
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test.multi();
        test.signal();
        System.out.println(1);

    }

    static void handle(Integer id) {
        for (int i = 0; i < 10000; i++) {
            int s = 0;
        }
    }

    static void multi() throws ExecutionException, InterruptedException {
        List<Integer> list = new LinkedList<>();
        List<String> res = new LinkedList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        List<Future<String>> futures = new LinkedList<>();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            int id = i;
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    handle(1);
                    return "list";
                }
            });
            futures.add(future);
        }
        for (Future<String> future : futures) {
            res.add(future.get());
        }
        executorService.shutdown();
        Long end = System.currentTimeMillis();
        System.out.println("多线程耗时:" + (end - start));
    }

    static void signal() {
        List<Integer> list = new LinkedList<>();
        List<String> res = new LinkedList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        List<Future<String>> futures = new LinkedList<>();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            test.handle(1);
        }

        Long end = System.currentTimeMillis();
        System.out.println("单线程耗时:" + (end - start));
    }
}
