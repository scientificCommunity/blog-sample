package org.baichuan.example.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

/**
 * @author kun
 * @date 2021-03-25 23:47
 */
public class Utils {
    static Semaphore semaphore;

    /**
     * 启动计时器，1秒打印一次
     */
    public static void startTimer() {
        new Thread(() -> {
            int i = 0;
            System.out.println("开始记时");
            while (true) {
                System.out.println("当前秒数：" + i++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 初始化vertx
     */
    public static Vertx initVertx() {
        VertxOptions vertxOptions = new VertxOptions();
        //分配3个eventLoop线程
        vertxOptions.setEventLoopPoolSize(3);
        return Vertx.vertx(vertxOptions);
    }

    /**
     * 使当前线程阻塞{@param seconds}秒
     */
    public static void block(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void initSemaphore(int permits) {
        semaphore = new Semaphore(permits);
    }

    @SneakyThrows
    public static void acquire() {
        semaphore.acquire();
    }

    public static void release() {
        semaphore.release();
    }

    public static final String SYMBOL = "========================";
}
