package com.example.threadlocal.case2;

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
public class AppPoolExample {

    private static InheritableThreadLocal<Integer> requestIdThreadLocal = new InheritableThreadLocal<>();
    public static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread:" + Thread.currentThread().getName() + " get value:" + requestIdThreadLocal.get());
            }
        };
        requestIdThreadLocal.set(1);
        executorService.submit(runnable);
        requestIdThreadLocal.set(2);
        executorService.submit(runnable);
        requestIdThreadLocal.set(3);
        executorService.submit(runnable);
        requestIdThreadLocal.set(4);
        executorService.submit(runnable);
        requestIdThreadLocal.set(5);
        executorService.submit(runnable);
        requestIdThreadLocal.set(6);
        executorService.submit(runnable);
        requestIdThreadLocal.set(7);
        executorService.submit(runnable);
        requestIdThreadLocal.set(8);
        executorService.submit(runnable);
        requestIdThreadLocal.set(9);
        executorService.submit(runnable);
        requestIdThreadLocal.set(10);
        executorService.submit(runnable);
        executorService.shutdown();
    }
}
