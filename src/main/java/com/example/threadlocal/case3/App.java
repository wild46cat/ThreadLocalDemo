package com.example.threadlocal.case3;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * /-------------------------------------------------------------------------\
 * |                                                                         |
 * |                  ***   ***           ***                                |
 * |                  ***   ***           ***                                |
 * |                  ***   ***           ***                         **     |
 * |                        ***           ***                        ***     |
 * |                        ***           ***                        ***     |
 * | ***    ***   *** ***   ***      **** ***     *****    *****   *******   |
 * | ***    ****  *** ***   ***    **********   *******   *******  *******   |
 * |  **   *****  **  ***   ***    **********   *******   *    *** *******   |
 * |  ***  *****  **  ***   ***   ****   ****  ****   *        ***   ***     |
 * |  ***  ** **  **  ***   ***   ***     ***  ***         *******   ***     |
 * |   **  ** ******  ***   ***   ***     ***  ***       *********   ***     |
 * |   *****  *****   ***   ***   ****   ****  ****   *  ***   ***   ***     |
 * |   *****  *****   ***   ***   ***********   *******  ***   ***   *****   |
 * |    ****   ****   ***   ***    ****** ***   *******  *********   *****   |
 * |    ***    ***    ***   ***     ****  ***     ****    **** ***    ****   |
 * |                                                                         |
 * \-------------------------------------------------------------------------/
 */
public class App {

    private static TransmittableThreadLocal<Integer> requestIdThreadLocal = new TransmittableThreadLocal<>();
    public static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executorService);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread:" + Thread.currentThread().getName() + " get value:" + requestIdThreadLocal.get());
            }
        };
        requestIdThreadLocal.set(1);
        ttlExecutorService.submit(runnable);
        requestIdThreadLocal.set(2);
        ttlExecutorService.submit(runnable);
        requestIdThreadLocal.set(3);
        ttlExecutorService.submit(runnable);
        requestIdThreadLocal.set(4);
        ttlExecutorService.submit(runnable);
        requestIdThreadLocal.set(5);
        ttlExecutorService.submit(runnable);
        requestIdThreadLocal.set(6);
        ttlExecutorService.submit(runnable);
        requestIdThreadLocal.set(7);
        ttlExecutorService.submit(runnable);
        requestIdThreadLocal.set(8);
        ttlExecutorService.submit(runnable);
        requestIdThreadLocal.set(9);
        ttlExecutorService.submit(runnable);
        requestIdThreadLocal.set(10);
        ttlExecutorService.submit(runnable);
        Thread.sleep(1000);
        executorService.shutdown();
    }
}
