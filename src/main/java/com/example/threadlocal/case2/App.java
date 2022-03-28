package com.example.threadlocal.case2;

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
    private static InheritableThreadLocal<Integer> requestIdThreadLocal = new InheritableThreadLocal<>();
    private static InheritableThreadLocal<Integer> requestIdThreadLocal2 = new InheritableThreadLocal<>();

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
