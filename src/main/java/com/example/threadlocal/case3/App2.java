package com.example.threadlocal.case3;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.Random;
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
public class App2 {

    private static TransmittableThreadLocal<Integer> requestIdThreadLocal = new TransmittableThreadLocal<>();
    public static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        //????????????
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executorService);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random(10);
                int i = random.nextInt();
                requestIdThreadLocal.set(i);
                System.out.println("thread:set:" + i);
                System.out.println("thread:" + Thread.currentThread().getName() + " get value:" + requestIdThreadLocal.get());
            }
        };

        //?????? replay???restore.????????????????????????????????????,????????????????????????????????????
        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
        requestIdThreadLocal.set(1);
        new Thread(ttlRunnable).start();
        Thread.sleep(1000);
        System.out.println("thread:" + Thread.currentThread().getName() + " get value:" + requestIdThreadLocal.get());
        executorService.shutdown();
    }
}
