package thread;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreadPoolTest {

    @Test
    void executor_Test() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("hello"));
    }

    @Test
    void executorService_Test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "hello!!";
        });

        System.out.println("before call result");
        String result = future.get();
        System.out.println(result);
    }

    @Test
    void threadPoolExecutor_test() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        assertEquals(3, executor.getPoolSize());
        assertEquals(2, executor.getQueue().size());
    }

    @Test
    void scheduledExecutor() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.schedule(() -> {
            System.out.println("hello!!");
        }, 2000, TimeUnit.MILLISECONDS);


        Thread.sleep(1000);
    }

}
