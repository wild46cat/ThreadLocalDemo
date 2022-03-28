package com.example.threadlocal.case1;

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
    private static ThreadLocal<Integer> requestIdThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Integer> requestIdThreadLocal2 = new ThreadLocal<>();

    public static void main(String[] args) {
        requestIdThreadLocal.set(1);
        requestIdThreadLocal2.set(2);
        System.out.println("mainThread get value:" + requestIdThreadLocal.get());
        System.out.println("mainThread get value:" + requestIdThreadLocal2.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("subThread get value:" + requestIdThreadLocal.get());
                System.out.println("subThread get value:" + requestIdThreadLocal2.get());
            }
        }).start();
    }

}
