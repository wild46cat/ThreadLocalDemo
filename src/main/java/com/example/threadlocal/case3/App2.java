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
        //注意这句
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

        //演示 replay和restore.正常情况下不是这样使用的,是内部使用这里仅用作展示
        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
        requestIdThreadLocal.set(1);
        new Thread(ttlRunnable).start();
        Thread.sleep(1000);
        System.out.println("thread:" + Thread.currentThread().getName() + " get value:" + requestIdThreadLocal.get());
        executorService.shutdown();
    }
}
