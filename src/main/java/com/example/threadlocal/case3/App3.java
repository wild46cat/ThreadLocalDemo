package com.example.threadlocal.case3;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.HashMap;
import java.util.Map;
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
public class App3 {

    private static TransmittableThreadLocal<Integer> requestIdThreadLocal = new TransmittableThreadLocal<>();
    private static TransmittableThreadLocal<Map<String, Integer>> requestIdThreadLocal2 = new TransmittableThreadLocal<Map<String, Integer>>();
    public static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executorService);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("thread:" + Thread.currentThread().getName() + " get value:" + requestIdThreadLocal.get());
                Map<String, Integer> stringIntegerMap = requestIdThreadLocal2.get();
                System.out.println("thread:" + Thread.currentThread().getName() + " get map:" + stringIntegerMap);
//                stringIntegerMap.put("aa", 6);
//                stringIntegerMap.put("bb", 88);
//                stringIntegerMap.put("cc", 33);
                requestIdThreadLocal.set(3);
                requestIdThreadLocal2.set(getStringIntegerMapNew());
                System.out.println("thread:" + Thread.currentThread().getName() + " set threadLocal integer 3");
                System.out.println("thread:" + Thread.currentThread().getName() + " set threadLocal map new");
                System.out.println("thread:" + Thread.currentThread().getName() + " get value:" + requestIdThreadLocal.get());
                System.out.println("thread:" + Thread.currentThread().getName() + " get map:" + requestIdThreadLocal2.get());
            }
        };
        Map<String, Integer> map = getStringIntegerMap();
        requestIdThreadLocal2.set(map);
        requestIdThreadLocal.set(1);
        ttlExecutorService.submit(runnable);
        Thread.sleep(1000);
        //执行完成，再次查看ThreadLocal
        System.out.println("thread:" + Thread.currentThread().getName() + " get value:" + requestIdThreadLocal.get());
        System.out.println("thread:" + Thread.currentThread().getName() + " get map:" + requestIdThreadLocal2.get());
        executorService.shutdown();
    }

    private static Map<String, Integer> getStringIntegerMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 1);
        map.put("bb", 2);
        return map;
    }

    private static Map<String, Integer> getStringIntegerMapNew() {
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 88);
        map.put("bb", 99);
        map.put("cc", 111);
        return map;
    }
}
